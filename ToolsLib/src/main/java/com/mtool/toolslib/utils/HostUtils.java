package com.mtool.toolslib.utils;

import android.content.Context;

import com.mtool.toolslib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mtool.toolslib.utils.DateUtil.getCurrentDayOfMonth;
import static com.mtool.toolslib.utils.DateUtil.getCurrentMonth;
import static com.mtool.toolslib.utils.DateUtil.getCurrentYear;

/***
 * JAVA 防劫持握手流程
 */
public class HostUtils {
    private static List<DomainChangeBean> changes = new ArrayList<>();
    private int count;
    private List<DomainBean.DataBean> data;
    private Context appContext;


    public interface IVerifyHost {
        void onVerify(DomainBean.DataBean domainBean, HostUtils hostUtils);

        void onDefaultHandShakeSuccess(String host);

        void onAllFailt();
    }

    public void defaultHandshakeVerify(final DomainBean.DataBean dataBean, final IVerifyHost iVerifyHost, String defaultHandshakeVerifyUrl) {
        String currentYear = getCurrentYear();
        String currentMonth = getCurrentMonth();
        String currentMonthDay = getCurrentDayOfMonth();

        int i = Integer.valueOf(currentMonthDay) * 2;
        String s = i < 10 ? "0" : "";
        String s1 = s + i;
        String responseStringBuffer = currentMonthDay +
                currentYear +
                currentMonth +
                s1 + dataBean.getSystemCode()
                + MD5Utils.toMD5(dataBean.getPrivateKey());
        final String responseMD5 = MD5Utils.toMD5(responseStringBuffer);

        String stringBuffer = dataBean.getSystemCode() +
                currentYear +
                currentMonth +
                currentMonthDay +
                currentMonthDay +
                MD5Utils.toMD5(dataBean.getPrivateKey());
        String requestMD5 = MD5Utils.toMD5(stringBuffer);

        Map<String, Object> p = new HashMap<>();
        p.put("date", "" + currentYear + currentMonth + currentMonthDay);
        p.put("host", dataBean.getSystemCode());
        p.put("check_dn", requestMD5);

//        post(dataBean.getDomain(), defaultHandshakeVerifyUrl, p)
//                .tag(this)
//                .execute(new StringCallback() {
//
//                    @Override
//                    public void onSuccess(String str) {
//                        try {
//                            str = str.substring(1, str.length() - 1);
//                            str = str.replaceAll("\\\\\"", "\"");
//                            JSONObject jsonObject = new JSONObject(str);
//                            if (jsonObject.getInt("stat") == 0) {
//                                if (responseMD5.equals(jsonObject.getJSONObject("data").getString("check_dn"))) {
//                                    HostUtils.this.onSuccess();
//                                    iVerifyHost.onDefaultHandShakeSuccess(dataBean.getDomain());
//                                } else {
//                                    HostUtils.this.equals(dataBean);
//                                }
//                            } else {
//                                HostUtils.this.equals(dataBean);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            HostUtils.this.equals(dataBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(com.lzy.okgo.model.Response response) {
//                        super.onError(response);
//                        HostUtils.this.onError(dataBean);
//                        if (isAllErorr()) {
//                            iVerifyHost.onAllFailt();
//                        }
//                    }
//                });
    }

    public void onSuccess() {
        isNeedUpdate();
    }

    private void isNeedUpdate() {
        count++;
        if (count == data.size()) {
            updateUrlStatus();
        }
    }

    private void updateUrlStatus() {
        String DOMAIN_HOST = appContext.getString(R.string.DOMAIN_HOST);
        String DOMAIN_UPDATE_PATH = appContext.getString(R.string.DOMAIN_UPDATE_PATH);

//        Map<String, Object> paramData = new HashMap<>();
//        paramData.put("changes", changes);
//        Map<String, Object> params = new HashMap<>();
//        params.put("uri", DOMAIN_UPDATE_PATH);
//        params.put("token", "");
//        params.put("paramData", paramData);
//        post(DOMAIN_HOST, DOMAIN_UPDATE_PATH, null)
//                .upString(JSON.toJSONString(params))
//                .execute(new CallbackForString() {
//                    @Override
//                    public void onSuccess(String str) {
//                        Log.e("cww", "提交域名请求信息" + str);
//                    }
//
//                });
    }


    private boolean isAllErorr() {
        return changes.size() == data.size();
    }

    public void onError(DomainBean.DataBean domainBean) {
        DomainChangeBean changeBean = new DomainChangeBean();
        changeBean.setId(String.valueOf(domainBean.getId()));
        changeBean.setDomainState("1");
        changeBean.setDomain(domainBean.getDomain());
        changeBean.setSystemCode(domainBean.getSystemCode());
        changes.add(changeBean);
        isNeedUpdate();
    }


    public class DomainBean {

        /**
         * stat : 0
         * data : [{"domainState":"0","operatorUser":"SystemOperator","remark":"","removed":0,"systemCode":"xpj","createTime":1505111973000,"domain":"https://www.pj78777.com","lastModifyTime":1505112284000,"createUser":"SystemCreate","id":20},{"domainState":"0","operatorUser":"SystemOperator","remark":"","removed":0,"systemCode":"xpj","createTime":1505112145000,"domain":"https://www.xpj88879.com","lastModifyTime":1505112288000,"createUser":"SystemCreate","id":21},{"domainState":"0","operatorUser":"SystemOperator","remark":"","removed":0,"systemCode":"xpj","createTime":1505112250000,"domain":"https://www.xpj01666.com","lastModifyTime":1505112291000,"createUser":"SystemCreate","id":22},{"domainState":"0","operatorUser":"SystemOperator","remark":"","removed":0,"systemCode":"xpj","createTime":1505112334000,"domain":"https://www.xpj07999.com","lastModifyTime":1505112334000,"createUser":"SystemCreate","id":23},{"domainState":"0","operatorUser":"SystemOperator","remark":"","removed":0,"systemCode":"xpj","createTime":1505112376000,"domain":"https://www.pj77000.com","lastModifyTime":1505112376000,"createUser":"SystemCreate","id":24}]
         */

        private int stat;
        private List<DataBean> data;

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {

            /**
             * domainState : 0
             * operatorUser : SystemOperator
             * remark :
             * privateKey : 123
             * removed : 0
             * systemCode : xpj
             * createTime : 1505111973000
             * domain : https://www.pj78777.com
             * lastModifyTime : 1505412744000
             * createUser : SystemCreate
             * id : 20
             */

            private String domainState;
            private String operatorUser;
            private String remark;
            private String privateKey;
            private int removed;
            private String systemCode;
            private long createTime;
            private String domain;
            private long lastModifyTime;
            private String createUser;
            private int id;

            public String getDomainState() {
                return domainState;
            }

            public void setDomainState(String domainState) {
                this.domainState = domainState;
            }

            public String getOperatorUser() {
                return operatorUser;
            }

            public void setOperatorUser(String operatorUser) {
                this.operatorUser = operatorUser;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getPrivateKey() {
                return privateKey;
            }

            public void setPrivateKey(String privateKey) {
                this.privateKey = privateKey;
            }

            public int getRemoved() {
                return removed;
            }

            public void setRemoved(int removed) {
                this.removed = removed;
            }

            public String getSystemCode() {
                return systemCode;
            }

            public void setSystemCode(String systemCode) {
                this.systemCode = systemCode;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public long getLastModifyTime() {
                return lastModifyTime;
            }

            public void setLastModifyTime(long lastModifyTime) {
                this.lastModifyTime = lastModifyTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class DomainChangeBean {

        /**
         * id : 数据id
         * domainState : 域名状态(0正常1不正常)
         */

        private String id;
        private String domainState;
        private String systemCode;
        private String domain;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDomainState() {
            return domainState;
        }

        public void setDomainState(String domainState) {
            this.domainState = domainState;
        }

        public String getSystemCode() {
            return systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }
}
