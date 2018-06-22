package com.yhdx.wms.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class LoginBean {

    private String  userCode;
    private String  password;
    private String  warehouseId;
    private String  appKey;
    private String  expected;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
