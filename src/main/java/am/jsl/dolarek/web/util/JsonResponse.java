package am.jsl.dolarek.web.util;

/**
 * JsonResponse jest używany w wywołaniach Ajax do wysyłania odpowiedzi.
 */
public class JsonResponse {
    /**
     * Flaga wskazująca status odpowiedzi.
     */
    private boolean error = false;
    private Object result = null;

    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
}