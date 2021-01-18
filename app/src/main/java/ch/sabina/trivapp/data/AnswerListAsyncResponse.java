package ch.sabina.trivapp.data;

import java.util.ArrayList;

import ch.sabina.trivapp.model.Question;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
