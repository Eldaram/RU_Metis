package src.main.java.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RollResult {
    public RollResult(String errorMsg) {
        this.isError = true;
        this.errorMsg = errorMsg;
    }

    public StringBuilder stringBuilder;
    public String result;
    public float totalResult;
    public int nmbDices;
    public boolean isError;
    public String errorMsg;
}
