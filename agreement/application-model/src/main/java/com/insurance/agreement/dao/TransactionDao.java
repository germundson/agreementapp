package com.insurance.agreement.dao;

import com.insurance.agreement.model.Log;

public interface TransactionDao {

  Log saveLog(Log log);
}
