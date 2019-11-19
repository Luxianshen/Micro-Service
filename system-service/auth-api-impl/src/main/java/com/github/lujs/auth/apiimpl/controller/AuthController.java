package com.github.lujs.auth.apiimpl.controller;

import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.service.RoleService;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单控制层
 * @Author: lujs
 * @Data: 2019/5/18 10:25
 * @version: 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController extends BaseController {


    public final RoleService roleService;

    /**
     * 获取用户权限
     */
    @PostMapping("/menu/getRolePermissionList")
    @Permission(action = Action.Skip)
    public List<String> getRolePermissionList(@RequestBody List<String> roles) {

        if (roles == null || roles.size() < 1) {
            return new ArrayList<>();
        }
        List<String> permissionList = roleService.getUserPermissionList(roles);

        permissionList.add("hi");
        permissionList.add("hello");
        return permissionList;
    }


    @Permission(action = Action.Skip)
    @RequestMapping(value = "/menu/userMenu", produces = "application/json; charset=utf-8")
    public Object testMenu() {
        return "[{\"id\":\"571365178965364736\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":0,\"children\":[],\"icon\":\"dashboard\",\"name\":\"首页\",\"url\":\"/\",\"redirect\":null,\"spread\":false,\"path\":\"/dashboard\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"首页\",\"roles\":null,\"remark\":\"首页\",\"newRecord\":false},{\"id\":\"571367565360762880\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":1,\"children\":[{\"id\":\"571367969767165952\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571367565360762880\",\"sort\":2,\"children\":[],\"icon\":\"\",\"name\":\"用户管理\",\"url\":\"/api/user/v1/user/**\",\"redirect\":null,\"spread\":false,\"path\":\"user\",\"component\":\"views/sys/user\",\"authority\":null,\"type\":\"0\",\"label\":\"用户管理\",\"roles\":null,\"remark\":\"用户管理\",\"newRecord\":false},{\"id\":\"571368181252362240\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571367565360762880\",\"sort\":8,\"children\":[],\"icon\":\"\",\"name\":\"部门管理\",\"url\":\"/api/user/v1/dept/**\",\"redirect\":null,\"spread\":false,\"path\":\"dept\",\"component\":\"views/sys/dept\",\"authority\":null,\"type\":\"0\",\"label\":\"部门管理\",\"roles\":null,\"remark\":\"部门管理\",\"newRecord\":false},{\"id\":\"571368627413061632\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571367565360762880\",\"sort\":9,\"children\":[],\"icon\":\"\",\"name\":\"角色管理\",\"url\":\"/api/user/v1/role/**\",\"redirect\":null,\"spread\":false,\"path\":\"role\",\"component\":\"views/sys/role\",\"authority\":null,\"type\":\"0\",\"label\":\"角色管理\",\"roles\":null,\"remark\":\"角色管理\",\"newRecord\":false},{\"id\":\"571369094226513920\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571367565360762880\",\"sort\":10,\"children\":[],\"icon\":\"\",\"name\":\"菜单管理\",\"url\":\"/api/user/v1/menu/**\",\"redirect\":null,\"spread\":false,\"path\":\"menu\",\"component\":\"views/sys/menu\",\"authority\":null,\"type\":\"0\",\"label\":\"菜单管理\",\"roles\":null,\"remark\":\"菜单管理\",\"newRecord\":false},{\"id\":\"571369709904203776\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571367565360762880\",\"sort\":11,\"children\":[],\"icon\":\"\",\"name\":\"终端管理\",\"url\":\"/api/user/v1/client/**\",\"redirect\":null,\"spread\":false,\"path\":\"client\",\"component\":\"views/sys/client\",\"authority\":null,\"type\":\"0\",\"label\":\"终端管理\",\"roles\":null,\"remark\":\"终端管理\",\"newRecord\":false},{\"id\":\"571369965811273728\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571367565360762880\",\"sort\":12,\"children\":[],\"icon\":\"\",\"name\":\"路由管理\",\"url\":\"/api/user/route/**\",\"redirect\":null,\"spread\":false,\"path\":\"route\",\"component\":\"views/sys/route\",\"authority\":null,\"type\":\"0\",\"label\":\"路由管理\",\"roles\":null,\"remark\":\"路由管理\",\"newRecord\":false}],\"icon\":\"component\",\"name\":\"系统管理\",\"url\":\"/api/user/v1/**\",\"redirect\":null,\"spread\":false,\"path\":\"/sys\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"系统管理\",\"roles\":null,\"remark\":\"系统管理\",\"newRecord\":false},{\"id\":\"581237996276289536\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":3,\"children\":[{\"id\":\"581238351663861760\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"581237996276289536\",\"sort\":1,\"children\":[],\"icon\":\"\",\"name\":\"单位管理\",\"url\":\"/api/user/tenant/**\",\"redirect\":null,\"spread\":false,\"path\":\"tenant\",\"component\":\"views/tenant/tenant\",\"authority\":null,\"type\":\"0\",\"label\":\"单位管理\",\"roles\":null,\"remark\":\"单位管理\",\"newRecord\":false}],\"icon\":\"component\",\"name\":\"租户中心\",\"url\":\"/api/user/v1/tenent/**\",\"redirect\":null,\"spread\":false,\"path\":\"/tenant\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"租户中心\",\"roles\":null,\"remark\":\"租户管理\",\"newRecord\":false},{\"id\":\"571361163502292992\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":7,\"children\":[{\"id\":\"571361526066319360\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571361163502292992\",\"sort\":30,\"children\":[],\"icon\":\"\",\"name\":\"日志监控\",\"url\":\"/api/user/v1/log/**\",\"redirect\":null,\"spread\":false,\"path\":\"log\",\"component\":\"views/monitor/log\",\"authority\":null,\"type\":\"0\",\"label\":\"日志监控\",\"roles\":null,\"remark\":\"日志监控\",\"newRecord\":false},{\"id\":\"571361746552492032\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571361163502292992\",\"sort\":31,\"children\":[],\"icon\":\"\",\"name\":\"consul监控\",\"url\":\"/api/monitor/service/**\",\"redirect\":null,\"spread\":false,\"path\":\"http://it99.club:8500\",\"component\":null,\"authority\":null,\"type\":\"0\",\"label\":\"consul监控\",\"roles\":null,\"remark\":\"consul监控\",\"newRecord\":false},{\"id\":\"571362994005610496\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571361163502292992\",\"sort\":32,\"children\":[],\"icon\":\"\",\"name\":\"zipkin监控\",\"url\":\"/api/monitor/**\",\"redirect\":null,\"spread\":false,\"path\":\"http://it99.club:9411/zipkin\",\"component\":null,\"authority\":null,\"type\":\"0\",\"label\":\"zipkin监控\",\"roles\":null,\"remark\":null,\"newRecord\":false},{\"id\":\"571363268497641472\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571361163502292992\",\"sort\":33,\"children\":[],\"icon\":\"\",\"name\":\"服务监控\",\"url\":\"/api/monitor/**\",\"redirect\":null,\"spread\":false,\"path\":\"http://it99.club:8085/admin\",\"component\":null,\"authority\":null,\"type\":\"0\",\"label\":\"服务监控\",\"roles\":null,\"remark\":null,\"newRecord\":false},{\"id\":\"571363537549660160\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571361163502292992\",\"sort\":34,\"children\":[],\"icon\":\"\",\"name\":\"接口文档\",\"url\":\"/api/monitor/**\",\"redirect\":null,\"spread\":false,\"path\":\"http://it99.club:8000/swagger-ui.html\",\"component\":null,\"authority\":null,\"type\":\"0\",\"label\":\"接口文档\",\"roles\":null,\"remark\":null,\"newRecord\":false}],\"icon\":\"chart\",\"name\":\"系统监控\",\"url\":\"/api/monitor/**\",\"redirect\":null,\"spread\":false,\"path\":\"/monitor\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"系统监控\",\"roles\":null,\"remark\":\"系统监控\",\"newRecord\":false},{\"id\":\"571352797233156096\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":8,\"children\":[{\"id\":\"571353230286655488\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571352797233156096\",\"sort\":1,\"children\":[],\"icon\":\"\",\"name\":\"课程管理\",\"url\":\"/api/exam/v1/course/**\",\"redirect\":null,\"spread\":false,\"path\":\"course\",\"component\":\"views/exam/course\",\"authority\":null,\"type\":\"0\",\"label\":\"课程管理\",\"roles\":null,\"remark\":\"课程管理\",\"newRecord\":false},{\"id\":\"571353525381107712\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571352797233156096\",\"sort\":2,\"children\":[],\"icon\":\"\",\"name\":\"考试管理\",\"url\":\"/api/exam/v1/examination/**\",\"redirect\":null,\"spread\":false,\"path\":\"exam\",\"component\":\"views/exam/exam\",\"authority\":null,\"type\":\"0\",\"label\":\"考试管理\",\"roles\":null,\"remark\":\"考试管理\",\"newRecord\":false},{\"id\":\"571353992756596736\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571352797233156096\",\"sort\":3,\"children\":[],\"icon\":\"\",\"name\":\"题库管理\",\"url\":\"/api/exam/v1/subject/**\",\"redirect\":null,\"spread\":false,\"path\":\"subject\",\"component\":\"views/exam/subject\",\"authority\":null,\"type\":\"0\",\"label\":\"题库管理\",\"roles\":null,\"remark\":\"题库管理\",\"newRecord\":false},{\"id\":\"571354428217626624\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571352797233156096\",\"sort\":4,\"children\":[],\"icon\":\"\",\"name\":\"成绩管理\",\"url\":\"/api/exam/v1/examRecord/**\",\"redirect\":null,\"spread\":false,\"path\":\"score\",\"component\":\"views/exam/examRecord\",\"authority\":null,\"type\":\"0\",\"label\":\"成绩管理\",\"roles\":null,\"remark\":\"成绩管理\",\"newRecord\":false},{\"id\":\"571354823258148864\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571352797233156096\",\"sort\":5,\"children\":[],\"icon\":\"\",\"name\":\"知识库\",\"url\":\"/api/exam/v1/knowledge/**\",\"redirect\":null,\"spread\":false,\"path\":\"knowledge\",\"component\":\"views/exam/knowledge\",\"authority\":null,\"type\":\"0\",\"label\":\"知识库\",\"roles\":null,\"remark\":\"知识库\",\"newRecord\":false}],\"icon\":\"nested\",\"name\":\"考务管理\",\"url\":\"/api/exam/**\",\"redirect\":null,\"spread\":false,\"path\":\"/exam\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"考务管理\",\"roles\":null,\"remark\":\"考务管理\",\"newRecord\":false},{\"id\":\"571351763521769472\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":10,\"children\":[{\"id\":\"571352087896657920\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571351763521769472\",\"sort\":30,\"children\":[],\"icon\":\"\",\"name\":\"附件列表\",\"url\":\"/api/user/v1/attachment/list\",\"redirect\":null,\"spread\":false,\"path\":\"list\",\"component\":\"views/attachment/list\",\"authority\":null,\"type\":\"0\",\"label\":\"附件列表\",\"roles\":null,\"remark\":\"附件列表\",\"newRecord\":false}],\"icon\":\"excel\",\"name\":\"附件管理\",\"url\":\"/api/user/v1/attachment/**\",\"redirect\":null,\"spread\":false,\"path\":\"/attachment\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"附件管理\",\"roles\":null,\"remark\":\"附件管理\",\"newRecord\":false},{\"id\":\"571348650370928640\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"-1\",\"sort\":30,\"children\":[{\"id\":\"571349816924311552\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571348650370928640\",\"sort\":29,\"children\":[],\"icon\":\"\",\"name\":\"个人资料\",\"url\":\"/api/user/v1/user/updateInfo\",\"redirect\":null,\"spread\":false,\"path\":\"message\",\"component\":\"views/personal/message\",\"authority\":null,\"type\":\"0\",\"label\":\"个人资料\",\"roles\":null,\"remark\":\"个人资料\",\"newRecord\":false},{\"id\":\"571350099653955584\",\"creator\":null,\"createDate\":null,\"modifier\":null,\"modifyDate\":null,\"delFlag\":0,\"applicationCode\":null,\"tenantCode\":null,\"ids\":null,\"idString\":null,\"code\":null,\"parent\":null,\"parentId\":\"571348650370928640\",\"sort\":30,\"children\":[],\"icon\":\"\",\"name\":\"修改密码\",\"url\":\"/api/user/v1/user/updateInfo\",\"redirect\":null,\"spread\":false,\"path\":\"password\",\"component\":\"views/personal/password\",\"authority\":null,\"type\":\"0\",\"label\":\"修改密码\",\"roles\":null,\"remark\":\"修改密码\",\"newRecord\":false}],\"icon\":\"form\",\"name\":\"个人管理\",\"url\":\"/api/user/v1/personal/**\",\"redirect\":null,\"spread\":false,\"path\":\"/personal\",\"component\":\"Layout\",\"authority\":null,\"type\":\"0\",\"label\":\"个人管理\",\"roles\":null,\"remark\":\"个人管理\",\"newRecord\":false}]";
    }

    /**
     * 获取用户角色
     */
    @PostMapping("/role/getUserRoleList")
    @Permission(action = Action.Skip)
    public List<String> getUserRoleList(@RequestParam("userId") Long userId) {

        return roleService.getUserRoleList(userId);
    }

}
