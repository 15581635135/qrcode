package com.cq.qrcode.common;

/**
 * 封装了和Restult相关的常量
 *
 * @author : chenqi
 * @date : 2019/12/14 17:03
 */
public final class ResultConstant {

    /**
     * 封装了所有Result对应的状态
     *
     */
    public final static class CODE {

        /**
         * 表示成功
         */
        public static final int SUCCESS = 200;

        /**
         * 表示失败
         */
        public static final int ERROR = 400;

    }

    /**
     * 封装了Result对应的信息
     *
     */
    public final static class MESSAGE {

        /**
         * 处理成功的默认消息.
         */
        public static final String DEFAULT_SUCCESS_MESSAGE = "业务处理成功.";

        /**
         * 处理失败的默认消息.
         */
        public static final String DEFAULT_ERROR_MESSAGE = "业务处理失败.";

        /**
         * 处理缺少参数的默认消息.
         */
        public static final String DEFAULT_MISS_PARAM_MESSAGE = "业务操作缺少参数.";

    }


}
