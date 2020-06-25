package com.alex.customerstatementprocessor.api.model;

import java.util.ArrayList;
import java.util.List;
import com.alex.customerstatementprocessor.statement.model.StatementError;

public class UploadResponse {

  private List<StatementError> errors = new ArrayList<>();

  public List<StatementError> getErrors() {
    return errors;
  }

  public void setErrors(List<StatementError> errors) {
    this.errors = errors;
  }

}
