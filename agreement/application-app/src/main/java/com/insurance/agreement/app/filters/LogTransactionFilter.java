package com.insurance.agreement.app.filters;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.insurance.agreement.app.annotations.Filter;
import com.insurance.agreement.dao.TransactionDao;
import com.insurance.agreement.model.Log;

import static java.nio.charset.StandardCharsets.UTF_8;

@Filter
public class LogTransactionFilter extends OncePerRequestFilter {

  private Logger log = LoggerFactory.getLogger(LogTransactionFilter.class);

  @Autowired
  private TransactionDao dao;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(request);
    String body = IOUtils.toString(wrappedRequest.getReader());
    log.debug("Incoming request: {}", wrappedRequest.getRequestURL());
    log.debug("Method: {}", wrappedRequest.getMethod());
    if (StringUtils.isNoneEmpty(body)) {
      log.debug("Body:\n{}", body);
    }

    dao.saveLog(new Log(wrappedRequest.getMethod(), body, ""));

    wrappedRequest.resetInputStream();
    filterChain.doFilter(wrappedRequest, response);
  }

  private static class ResettableStreamHttpServletRequest extends
      HttpServletRequestWrapper {

    private byte[] rawData;
    private HttpServletRequest request;
    private ResettableServletInputStream servletStream;

    ResettableStreamHttpServletRequest(HttpServletRequest request) {
      super(request);
      this.request = request;
      this.servletStream = new ResettableServletInputStream();
    }

    void resetInputStream() {
      servletStream.stream = new ByteArrayInputStream(rawData);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
      if (rawData == null) {
        rawData = IOUtils.toByteArray(this.request.getReader(), UTF_8);
        servletStream.stream = new ByteArrayInputStream(rawData);
      }
      return servletStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
      if (rawData == null) {
        rawData = IOUtils.toByteArray(this.request.getReader(), UTF_8);
        servletStream.stream = new ByteArrayInputStream(rawData);
      }
      return new BufferedReader(new InputStreamReader(servletStream));
    }

    private class ResettableServletInputStream extends ServletInputStream {

      private Logger logger = LoggerFactory.getLogger(ResettableServletInputStream.class);
      private InputStream stream;

      @Override
      public int read() throws IOException {
        return stream.read();
      }

      @Override
      public boolean isFinished() {
        try {
          return stream.available() == 0;
        }
        catch (IOException e) {
          logger.warn(e.getMessage(), e);
          return false;
        }
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener listener) {
        logger.warn("Not implemented");
      }
    }
  }
}
