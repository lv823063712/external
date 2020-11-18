package com.example.external.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @ClassName: ProductBean
 * @Description:
 * @CreateDate: 2020/11/16 22:26
 * @Creator: lf
 */

public class ProductBean implements Parcelable {

    /**
     * status : 1
     * code : 0001
     * message : success
     * data : {"limits":[{"amount":30000,"is_default":0,"durations":[{"id":"4f81a20225abfec4f0c40e7d5c510621","amount":30000,"interest":300,"duration":"1 month","monthly_payment":30300,"monthly_principal":30000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"8dd185f2c322cc6facad31dee9a9b604","amount":30000,"interest":900,"duration":"3 months","monthly_payment":10300,"monthly_principal":10000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"b6538cb79b75f175563efc5e01133b93","amount":30000,"interest":1800,"duration":"6 months","monthly_payment":5300,"monthly_principal":5000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"d76fdb3799bfbf5fad1e5d89fd5ab819","amount":30000,"interest":3600,"duration":"12 months","monthly_payment":2800,"monthly_principal":2500,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0}]},{"amount":50000,"is_default":0,"durations":[{"id":"27e8e4ba7fdfc13d53b553dc90454d2e","amount":50000,"interest":500,"duration":"1 month","monthly_payment":50500,"monthly_principal":50000,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0},{"id":"b750a92ab44911ff5012e1670c6254b6","amount":50000,"interest":1500,"duration":"3 months","monthly_payment":17167,"monthly_principal":16667,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0},{"id":"d3406c1c7c7b9598500a60dbd36e6445","amount":50000,"interest":3000,"duration":"6 months","monthly_payment":8833,"monthly_principal":8333,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0},{"id":"f1883a1fd0e7415ce79e0c9786490a46","amount":50000,"interest":6000,"duration":"12 months","monthly_payment":4667,"monthly_principal":4167,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0}]},{"amount":80000,"is_default":1,"durations":[{"id":"21720df198df2d4230544c4cfc597cc9","amount":80000,"interest":800,"duration":"1 month","monthly_payment":80800,"monthly_principal":80000,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":1},{"id":"654f48d6843f98627d64d8aee8065d95","amount":80000,"interest":2400,"duration":"3 months","monthly_payment":27467,"monthly_principal":26667,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":0},{"id":"a37157660ca542f43a7b4b6df9aae75d","amount":80000,"interest":4800,"duration":"6 months","monthly_payment":14133,"monthly_principal":13333,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":0},{"id":"e86c6199e58e677abdad69c79186a165","amount":80000,"interest":9600,"duration":"12 months","monthly_payment":7467,"monthly_principal":6667,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":0}]},{"amount":150000,"is_default":0,"durations":[{"id":"038811065cde2759e85d67d31c3496aa","amount":150000,"interest":1500,"duration":"1 month","monthly_payment":151500,"monthly_principal":150000,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0},{"id":"579e9b8da5636890f30397c7ab1b414a","amount":150000,"interest":4500,"duration":"3 months","monthly_payment":51500,"monthly_principal":50000,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0},{"id":"5b76b485ec6aee0cff6c9f19a38c3f09","amount":150000,"interest":9000,"duration":"6 months","monthly_payment":26500,"monthly_principal":25000,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0},{"id":"79c5d5af076bb27b27384f54f6e3a271","amount":150000,"interest":18000,"duration":"12 months","monthly_payment":14000,"monthly_principal":12500,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0}]}],"viplist":[],"certification":0,"phase":2}
     */

    private int status;
    private String code;
    private String message;
    private DataBean data;

    protected ProductBean(Parcel in) {
        status = in.readInt();
        code = in.readString();
        message = in.readString();
        data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<ProductBean> CREATOR = new Creator<ProductBean>() {
        @Override
        public ProductBean createFromParcel(Parcel in) {
            return new ProductBean(in);
        }

        @Override
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(code);
        dest.writeString(message);
        dest.writeParcelable(data, flags);
    }

    public static class DataBean implements Parcelable{
        /**
         * limits : [{"amount":30000,"is_default":0,"durations":[{"id":"4f81a20225abfec4f0c40e7d5c510621","amount":30000,"interest":300,"duration":"1 month","monthly_payment":30300,"monthly_principal":30000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"8dd185f2c322cc6facad31dee9a9b604","amount":30000,"interest":900,"duration":"3 months","monthly_payment":10300,"monthly_principal":10000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"b6538cb79b75f175563efc5e01133b93","amount":30000,"interest":1800,"duration":"6 months","monthly_payment":5300,"monthly_principal":5000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"d76fdb3799bfbf5fad1e5d89fd5ab819","amount":30000,"interest":3600,"duration":"12 months","monthly_payment":2800,"monthly_principal":2500,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0}]},{"amount":50000,"is_default":0,"durations":[{"id":"27e8e4ba7fdfc13d53b553dc90454d2e","amount":50000,"interest":500,"duration":"1 month","monthly_payment":50500,"monthly_principal":50000,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0},{"id":"b750a92ab44911ff5012e1670c6254b6","amount":50000,"interest":1500,"duration":"3 months","monthly_payment":17167,"monthly_principal":16667,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0},{"id":"d3406c1c7c7b9598500a60dbd36e6445","amount":50000,"interest":3000,"duration":"6 months","monthly_payment":8833,"monthly_principal":8333,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0},{"id":"f1883a1fd0e7415ce79e0c9786490a46","amount":50000,"interest":6000,"duration":"12 months","monthly_payment":4667,"monthly_principal":4167,"monthly_inerest":500,"member_ori_fee":497,"member_fee":397,"is_default":0}]},{"amount":80000,"is_default":1,"durations":[{"id":"21720df198df2d4230544c4cfc597cc9","amount":80000,"interest":800,"duration":"1 month","monthly_payment":80800,"monthly_principal":80000,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":1},{"id":"654f48d6843f98627d64d8aee8065d95","amount":80000,"interest":2400,"duration":"3 months","monthly_payment":27467,"monthly_principal":26667,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":0},{"id":"a37157660ca542f43a7b4b6df9aae75d","amount":80000,"interest":4800,"duration":"6 months","monthly_payment":14133,"monthly_principal":13333,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":0},{"id":"e86c6199e58e677abdad69c79186a165","amount":80000,"interest":9600,"duration":"12 months","monthly_payment":7467,"monthly_principal":6667,"monthly_inerest":800,"member_ori_fee":597,"member_fee":497,"is_default":0}]},{"amount":150000,"is_default":0,"durations":[{"id":"038811065cde2759e85d67d31c3496aa","amount":150000,"interest":1500,"duration":"1 month","monthly_payment":151500,"monthly_principal":150000,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0},{"id":"579e9b8da5636890f30397c7ab1b414a","amount":150000,"interest":4500,"duration":"3 months","monthly_payment":51500,"monthly_principal":50000,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0},{"id":"5b76b485ec6aee0cff6c9f19a38c3f09","amount":150000,"interest":9000,"duration":"6 months","monthly_payment":26500,"monthly_principal":25000,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0},{"id":"79c5d5af076bb27b27384f54f6e3a271","amount":150000,"interest":18000,"duration":"12 months","monthly_payment":14000,"monthly_principal":12500,"monthly_inerest":1500,"member_ori_fee":797,"member_fee":697,"is_default":0}]}]
         * viplist : []
         * certification : 0
         * phase : 2
         */

        private int certification;
        private int phase;
        private List<LimitsBean> limits;
        private List<?> viplist;

        protected DataBean(Parcel in) {
            certification = in.readInt();
            phase = in.readInt();
            limits = in.createTypedArrayList(LimitsBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getCertification() {
            return certification;
        }

        public void setCertification(int certification) {
            this.certification = certification;
        }

        public int getPhase() {
            return phase;
        }

        public void setPhase(int phase) {
            this.phase = phase;
        }

        public List<LimitsBean> getLimits() {
            return limits;
        }

        public void setLimits(List<LimitsBean> limits) {
            this.limits = limits;
        }

        public List<?> getViplist() {
            return viplist;
        }

        public void setViplist(List<?> viplist) {
            this.viplist = viplist;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(certification);
            dest.writeInt(phase);
            dest.writeTypedList(limits);
        }

        public static class LimitsBean implements Parcelable{
            /**
             * amount : 30000
             * is_default : 0
             * durations : [{"id":"4f81a20225abfec4f0c40e7d5c510621","amount":30000,"interest":300,"duration":"1 month","monthly_payment":30300,"monthly_principal":30000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"8dd185f2c322cc6facad31dee9a9b604","amount":30000,"interest":900,"duration":"3 months","monthly_payment":10300,"monthly_principal":10000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"b6538cb79b75f175563efc5e01133b93","amount":30000,"interest":1800,"duration":"6 months","monthly_payment":5300,"monthly_principal":5000,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0},{"id":"d76fdb3799bfbf5fad1e5d89fd5ab819","amount":30000,"interest":3600,"duration":"12 months","monthly_payment":2800,"monthly_principal":2500,"monthly_inerest":300,"member_ori_fee":397,"member_fee":297,"is_default":0}]
             */

            private int amount;
            private int is_default;
            private List<DurationsBean> durations;

            protected LimitsBean(Parcel in) {
                amount = in.readInt();
                is_default = in.readInt();
                durations = in.createTypedArrayList(DurationsBean.CREATOR);
            }

            public static final Creator<LimitsBean> CREATOR = new Creator<LimitsBean>() {
                @Override
                public LimitsBean createFromParcel(Parcel in) {
                    return new LimitsBean(in);
                }

                @Override
                public LimitsBean[] newArray(int size) {
                    return new LimitsBean[size];
                }
            };

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getIs_default() {
                return is_default;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }

            public List<DurationsBean> getDurations() {
                return durations;
            }

            public void setDurations(List<DurationsBean> durations) {
                this.durations = durations;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(amount);
                dest.writeInt(is_default);
                dest.writeTypedList(durations);
            }

            public static class DurationsBean implements Parcelable {
                /**
                 * id : 4f81a20225abfec4f0c40e7d5c510621
                 * amount : 30000
                 * interest : 300
                 * duration : 1 month
                 * monthly_payment : 30300
                 * monthly_principal : 30000
                 * monthly_inerest : 300
                 * member_ori_fee : 397
                 * member_fee : 297
                 * is_default : 0
                 */

                private String id;
                private int amount;
                private int interest;
                private String duration;
                private int monthly_payment;
                private int monthly_principal;
                private int monthly_inerest;
                private int member_ori_fee;
                private int member_fee;
                private int is_default;

                protected DurationsBean(Parcel in) {
                    id = in.readString();
                    amount = in.readInt();
                    interest = in.readInt();
                    duration = in.readString();
                    monthly_payment = in.readInt();
                    monthly_principal = in.readInt();
                    monthly_inerest = in.readInt();
                    member_ori_fee = in.readInt();
                    member_fee = in.readInt();
                    is_default = in.readInt();
                }

                public static final Creator<DurationsBean> CREATOR = new Creator<DurationsBean>() {
                    @Override
                    public DurationsBean createFromParcel(Parcel in) {
                        return new DurationsBean(in);
                    }

                    @Override
                    public DurationsBean[] newArray(int size) {
                        return new DurationsBean[size];
                    }
                };

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getInterest() {
                    return interest;
                }

                public void setInterest(int interest) {
                    this.interest = interest;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public int getMonthly_payment() {
                    return monthly_payment;
                }

                public void setMonthly_payment(int monthly_payment) {
                    this.monthly_payment = monthly_payment;
                }

                public int getMonthly_principal() {
                    return monthly_principal;
                }

                public void setMonthly_principal(int monthly_principal) {
                    this.monthly_principal = monthly_principal;
                }

                public int getMonthly_inerest() {
                    return monthly_inerest;
                }

                public void setMonthly_inerest(int monthly_inerest) {
                    this.monthly_inerest = monthly_inerest;
                }

                public int getMember_ori_fee() {
                    return member_ori_fee;
                }

                public void setMember_ori_fee(int member_ori_fee) {
                    this.member_ori_fee = member_ori_fee;
                }

                public int getMember_fee() {
                    return member_fee;
                }

                public void setMember_fee(int member_fee) {
                    this.member_fee = member_fee;
                }

                public int getIs_default() {
                    return is_default;
                }

                public void setIs_default(int is_default) {
                    this.is_default = is_default;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeInt(amount);
                    dest.writeInt(interest);
                    dest.writeString(duration);
                    dest.writeInt(monthly_payment);
                    dest.writeInt(monthly_principal);
                    dest.writeInt(monthly_inerest);
                    dest.writeInt(member_ori_fee);
                    dest.writeInt(member_fee);
                    dest.writeInt(is_default);
                }
            }
        }
    }
}
