package com.bonc.epm.ui.renderEngine.exception;

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/11/08
 *@desc SourceLoaderException
 */
public class SourceLoaderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private boolean propertiesKey = true;

    public SourceLoaderException(String message) {
        super(message);
    }

    public SourceLoaderException(String errorCode, String message) {
        this(errorCode, message, true);
    }

    public SourceLoaderException(String errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, true);
    }

    public SourceLoaderException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public SourceLoaderException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public SourceLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

}
