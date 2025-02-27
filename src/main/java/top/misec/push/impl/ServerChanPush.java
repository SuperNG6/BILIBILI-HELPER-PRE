package top.misec.push.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import top.misec.api.ApiList;
import top.misec.push.AbstractPush;
import top.misec.push.model.PushMetaInfo;

/**
 * server酱推送 .
 * Server酱旧版推送渠道即将下线，使用Turbo版本{@link ServerChanTurboPush}替代 .
 *
 * @author itning
 * @since 2021/3/22 16:37
 */
public class ServerChanPush extends AbstractPush {

    @Override
    protected String generatePushUrl(PushMetaInfo metaInfo) {
        return ApiList.SERVER_PUSH + metaInfo.getToken() + ".send";
    }

    @Override
    protected boolean checkPushStatus(JsonObject jsonObject) {
        if (null == jsonObject) {
            return false;
        }

        JsonElement code = jsonObject.get("code");

        if (null == code) {
            return false;
        }

        return code.getAsInt() == 0;
    }

    @Override
    protected String generatePushBody(PushMetaInfo metaInfo, String content) {
        return "text=BILIBILI-HELPER任务简报&desp=" + content;
    }
}