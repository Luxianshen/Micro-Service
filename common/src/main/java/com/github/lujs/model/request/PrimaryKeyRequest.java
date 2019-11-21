package com.github.lujs.model.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/21 16:51
 */
public class PrimaryKeyRequest implements Serializable {

    @NotNull(
            message = "id必填"
    )
    private Long id;

    public PrimaryKeyRequest() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PrimaryKeyRequest)) {
            return false;
        } else {
            PrimaryKeyRequest other = (PrimaryKeyRequest)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PrimaryKeyRequest;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PrimaryKeyRequest(id=" + this.getId() + ")";
    }
}
