package jjuni.pjt.recommand.common.model;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Getter
public class RecommandResponse {
//    private Integer status;
//    private String message;
//    private Object result;
//
//    private RecommandResponse(Builder builder) {
//        this.status = builder.status;
//        MessageSource messageSource = ApplicationContextUtils.getBean(MessageSource.class);
//        this.message = messageSource.getMessage(builder.getMessageKey(), builder.getMessageParameters(), "", LocaleContextHolder.getLocale());
//        this.result = builder.result;
//    }
//
//    public static Builder newBuilder() {
//        return new Builder();
//    }
//
//    public String toJson() {
//        return new Gson().toJson(this);
//    }
//
//    @Getter
//    public static final class Builder {
//        private Integer status;
//        private String messageKey;
//        private Object[] messageParameters;
//        private Object result;
//
//        private Builder() {
//        }
//
//        public Builder status(Integer status) {
//            this.status = status;
//
//            return this;
//        }
//
//        public Builder messageKey(String messageKey) {
//            this.messageKey = messageKey;
//
//            return this;
//        }
//
//        public Builder messageParameters(Object[] messageParameters) {
//            this.messageParameters = messageParameters;
//
//            return this;
//        }
//
//        public Builder result(Object result) {
//            this.result = result;
//
//            return this;
//        }
//
//        public AiccResponse build() {
//            AiccResponse aiccResponse = new AiccResponse(this);
//            return aiccResponse;
//        }
//    }
}
