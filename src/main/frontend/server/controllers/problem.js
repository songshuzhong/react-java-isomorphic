/**
 * Created by renxuanwei on 2017/8/14.
 */
const controller = {

    getCommonProblem (ctx, next) {
        ctx.body = [
            {
                commonTitle : '平台入驻',
                problems : [
                    {
                        problemTitle : '如何入驻能力开放平台',
                        problemContent : '在能力开放平台首页，点击“注册”，填写相关信息，提交入驻申请，由平台审核之后即可入驻；审核通过后，登录账号和密码信息会通过邮箱发送。'
                    },
                    {
                        problemTitle : '入驻平台有什么要求',
                        problemContent : '平台会根据用户提交的入驻申请信息，决定是否接纳用户入驻。'
                    }
                ]
            }
        ]
    }
};

module.exports = controller;