package com.y.gui.vo;

import com.y.gui.common.bases.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class UserVO extends Page {
    @Schema(name = "name", title = "用户的", description = "用户名称")
    private String name;

    private Integer age;

    private Integer gender;

    private String address;

    public UserVO(Long total, Object data) {
        super(total, data);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("u_");
        if (!StringUtils.isEmpty(this.name)) {
            builder.append("name:" + this.name);
        }
        if (null != this.age) {
            builder.append("age:" + this.age);
        }
        if (null != this.gender) {
            builder.append("gender:" + this.gender);
        }
        if (!StringUtils.isEmpty(this.address)) {
            builder.append("address:" + this.address);
        }
        return builder.toString().hashCode() + "";
    }
}
