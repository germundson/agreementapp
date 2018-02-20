package com.insurance.agreement.service.impl;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.insurance.agreement.api.ping.PingApi;
import com.insurance.agreement.dao.PingDao;
import com.insurance.agreement.service.PingService;

@Component
@Service
public class PingServiceImpl implements PingService {

  private final PingDao pingDao;
  private String buildDate;
  private Logger logger = LoggerFactory.getLogger(PingServiceImpl.class);

  @Autowired
  public PingServiceImpl(PingDao pingDao,
      @Value("${build.date}") String buildDate) {
    this.pingDao = pingDao;
    this.buildDate = buildDate;
  }

  @Override
  public PingApi ping() {

    long start = System.currentTimeMillis();
    boolean db = false;

    try {
      pingDao.ping();
      db = true;
    }
    catch (Exception ex) {
      logger.warn(ex.getMessage(), ex);
      db = false;
    }

    PingApi api = new PingApi();
    api.db = db;
    api.buildTime = buildDate;

    api.online = db;

    MavenXpp3Reader reader = new MavenXpp3Reader();
    try {
      Model model = reader.read(new FileReader("pom.xml"));
      api.version = model.getParent() != null ? model.getParent().getVersion() : model.getVersion();
    }
    catch (IOException | XmlPullParserException e) {
      logger.warn(e.getMessage(), e);
    }

    api.system = "TEST";
    api.statusTime = System.currentTimeMillis() - start;
    return api;
  }
}
