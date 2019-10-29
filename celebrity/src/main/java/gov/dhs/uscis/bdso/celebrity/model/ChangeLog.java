package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import gov.dhs.uscis.bdso.celebrity.model.Changes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ChangeLog
 */

public class ChangeLog   {
  @JsonProperty("tableName")
  private String tableName;

  @JsonProperty("tableId")
  private String tableId;

  @JsonProperty("fieldName")
  private String fieldName;

  @JsonProperty("changeDate")
  private OffsetDateTime changeDate;

  @JsonProperty("changedBy")
  private String changedBy;

  @JsonProperty("changes")
  private Changes changes;

  public ChangeLog tableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  /**
   * Get tableName
   * @return tableName
  */
  @ApiModelProperty(value = "")


  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public ChangeLog tableId(String tableId) {
    this.tableId = tableId;
    return this;
  }

  /**
   * Get tableId
   * @return tableId
  */
  @ApiModelProperty(value = "")


  public String getTableId() {
    return tableId;
  }

  public void setTableId(String tableId) {
    this.tableId = tableId;
  }

  public ChangeLog fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  /**
   * Get fieldName
   * @return fieldName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public ChangeLog changeDate(OffsetDateTime changeDate) {
    this.changeDate = changeDate;
    return this;
  }

  /**
   * Get changeDate
   * @return changeDate
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getChangeDate() {
    return changeDate;
  }

  public void setChangeDate(OffsetDateTime changeDate) {
    this.changeDate = changeDate;
  }

  public ChangeLog changedBy(String changedBy) {
    this.changedBy = changedBy;
    return this;
  }

  /**
   * Get changedBy
   * @return changedBy
  */
  @ApiModelProperty(value = "")


  public String getChangedBy() {
    return changedBy;
  }

  public void setChangedBy(String changedBy) {
    this.changedBy = changedBy;
  }

  public ChangeLog changes(Changes changes) {
    this.changes = changes;
    return this;
  }

  /**
   * Get changes
   * @return changes
  */
  @ApiModelProperty(value = "")

  @Valid

  public Changes getChanges() {
    return changes;
  }

  public void setChanges(Changes changes) {
    this.changes = changes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeLog changeLog = (ChangeLog) o;
    return Objects.equals(this.tableName, changeLog.tableName) &&
        Objects.equals(this.tableId, changeLog.tableId) &&
        Objects.equals(this.fieldName, changeLog.fieldName) &&
        Objects.equals(this.changeDate, changeLog.changeDate) &&
        Objects.equals(this.changedBy, changeLog.changedBy) &&
        Objects.equals(this.changes, changeLog.changes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tableName, tableId, fieldName, changeDate, changedBy, changes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeLog {\n");
    
    sb.append("    tableName: ").append(toIndentedString(tableName)).append("\n");
    sb.append("    tableId: ").append(toIndentedString(tableId)).append("\n");
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    changeDate: ").append(toIndentedString(changeDate)).append("\n");
    sb.append("    changedBy: ").append(toIndentedString(changedBy)).append("\n");
    sb.append("    changes: ").append(toIndentedString(changes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

