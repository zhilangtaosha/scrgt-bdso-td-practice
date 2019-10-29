package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Changes
 */

public class Changes   {
  @JsonProperty("newValue")
  private String newValue;

  @JsonProperty("oldValue")
  private String oldValue;

  public Changes newValue(String newValue) {
    this.newValue = newValue;
    return this;
  }

  /**
   * Get newValue
   * @return newValue
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }

  public Changes oldValue(String oldValue) {
    this.oldValue = oldValue;
    return this;
  }

  /**
   * Get oldValue
   * @return oldValue
  */
  @ApiModelProperty(value = "")


  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Changes changes = (Changes) o;
    return Objects.equals(this.newValue, changes.newValue) &&
        Objects.equals(this.oldValue, changes.oldValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newValue, oldValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Changes {\n");
    
    sb.append("    newValue: ").append(toIndentedString(newValue)).append("\n");
    sb.append("    oldValue: ").append(toIndentedString(oldValue)).append("\n");
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

