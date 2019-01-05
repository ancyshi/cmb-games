package cn.fulugame.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *管理员用户,平台操作人员
 * </p>
 *
 * @author ybp
 * @since 2018-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String username;//用户名
    private String nickname;//昵称
    private String mobile;//手机号
    private String password;//密码
    private String reasons;//禁用原因
    private Integer state;//状态 0禁用 1开启
    private String captcha;
    private List<Integer> resIds;
    private String confirmPassword;//确认密码
    private String newPassword;//新密码
    public User() {
    }
}
