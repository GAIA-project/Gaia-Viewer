package viewer.android.gaiaproject.eu.gaiaviewer.aa;

public class SwAACheckTokenResponse {
    private String user_name;
    private String client_id;
    private long exp;
    private String error;
    private String error_description;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "SwAACheckTokenResponse{" + "user_name='" + user_name + '\'' + ", client_id='" + client_id + '\'' + ", exp=" + exp + ", error='" + error + '\'' + ", error_description='" + error_description + '\'' + '}';
    }
}
