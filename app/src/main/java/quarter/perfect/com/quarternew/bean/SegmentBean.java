package quarter.perfect.com.quarternew.bean;

import java.util.List;

/**
 * Created by liyongkai on 2018/4/19.
 */

public class SegmentBean {

    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"周少博","createTime":"2018-04-19T10:04:55","imgUrls":null,"jid":1996,"praiseNum":null,"shareNum":null,"uid":2809,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1523961484387touxiang.jpg","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"蜜蜂","createTime":"2018-04-19T10:04:17","imgUrls":null,"jid":1995,"praiseNum":null,"shareNum":null,"uid":2809,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1523961484387touxiang.jpg","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"你林超级酷","createTime":"2018-04-19T08:18:50","imgUrls":null,"jid":1994,"praiseNum":null,"shareNum":null,"uid":12457,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524097027661IMG_3986.JPG","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"你林超级酷","createTime":"2018-04-19T08:12:45","imgUrls":null,"jid":1993,"praiseNum":null,"shareNum":null,"uid":12457,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524097027661IMG_3986.JPG","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"艹","createTime":"2018-04-18T14:27:54","imgUrls":"https://www.zhaoapi.cn/images/quarter/1524032874974IMG20180417073040.jpg|https://www.zhaoapi.cn/images/quarter/1524032874974IMG20180416215914.jpg|https://www.zhaoapi.cn/images/quarter/1524032874989IMG20180417222758.jpg","jid":1992,"praiseNum":null,"shareNum":null,"uid":3026,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524033488755test.png","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"艹","createTime":"2018-04-18T14:27:51","imgUrls":"https://www.zhaoapi.cn/images/quarter/1524032871599IMG20180417073040.jpg|https://www.zhaoapi.cn/images/quarter/1524032871614IMG20180416215914.jpg|https://www.zhaoapi.cn/images/quarter/1524032871614IMG20180417222758.jpg","jid":1991,"praiseNum":null,"shareNum":null,"uid":3026,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524033488755test.png","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"艹","createTime":"2018-04-18T14:27:50","imgUrls":"https://www.zhaoapi.cn/images/quarter/1524032870161IMG20180417073040.jpg|https://www.zhaoapi.cn/images/quarter/1524032870177IMG20180416215914.jpg|https://www.zhaoapi.cn/images/quarter/1524032870177IMG20180417222758.jpg","jid":1990,"praiseNum":null,"shareNum":null,"uid":3026,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524033488755test.png","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"艹","createTime":"2018-04-18T14:27:49","imgUrls":"https://www.zhaoapi.cn/images/quarter/1524032869411IMG20180417073040.jpg|https://www.zhaoapi.cn/images/quarter/1524032869427IMG20180416215914.jpg|https://www.zhaoapi.cn/images/quarter/1524032869427IMG20180417222758.jpg","jid":1989,"praiseNum":null,"shareNum":null,"uid":3026,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524033488755test.png","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"艹","createTime":"2018-04-18T14:27:49","imgUrls":"https://www.zhaoapi.cn/images/quarter/1524032869114IMG20180417073040.jpg|https://www.zhaoapi.cn/images/quarter/1524032869130IMG20180416215914.jpg|https://www.zhaoapi.cn/images/quarter/1524032869130IMG20180417222758.jpg","jid":1988,"praiseNum":null,"shareNum":null,"uid":3026,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524033488755test.png","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"艹","createTime":"2018-04-18T14:27:48","imgUrls":"https://www.zhaoapi.cn/images/quarter/1524032868833IMG20180417073040.jpg|https://www.zhaoapi.cn/images/quarter/1524032868833IMG20180416215914.jpg|https://www.zhaoapi.cn/images/quarter/1524032868849IMG20180417222758.jpg","jid":1987,"praiseNum":null,"shareNum":null,"uid":3026,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1524033488755test.png","nickname":null,"praiseNum":"null"}}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : null
         * content : 周少博
         * createTime : 2018-04-19T10:04:55
         * imgUrls : null
         * jid : 1996
         * praiseNum : null
         * shareNum : null
         * uid : 2809
         * user : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1523961484387touxiang.jpg","nickname":null,"praiseNum":"null"}
         */

        private Object commentNum;
        private String content;
        private String createTime;
        private Object imgUrls;
        private int jid;
        private Object praiseNum;
        private Object shareNum;
        private int uid;
        private UserBean user;

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(Object imgUrls) {
            this.imgUrls = imgUrls;
        }

        public int getJid() {
            return jid;
        }

        public void setJid(int jid) {
            this.jid = jid;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public Object getShareNum() {
            return shareNum;
        }

        public void setShareNum(Object shareNum) {
            this.shareNum = shareNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : false
             * icon : https://www.zhaoapi.cn/images/1523961484387touxiang.jpg
             * nickname : null
             * praiseNum : null
             */

            private Object age;
            private String fans;
            private boolean follow;
            private String icon;
            private Object nickname;
            private String praiseNum;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public boolean isFollow() {
                return follow;
            }

            public void setFollow(boolean follow) {
                this.follow = follow;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }
        }
    }
}
