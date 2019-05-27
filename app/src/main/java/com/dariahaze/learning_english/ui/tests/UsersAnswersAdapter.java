package com.dariahaze.learning_english.ui.tests;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.RandomQuestion;

import java.util.List;

public class UsersAnswersAdapter  extends RecyclerView.Adapter<UsersAnswersAdapter.ViewHolder> {
    private List<Integer> answers;
    private List<RandomQuestion> questionList;

    public UsersAnswersAdapter(List<Integer> answers, List<RandomQuestion> questionList) {
        this.answers = answers;
        this.questionList = questionList;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public List<RandomQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<RandomQuestion> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public UsersAnswersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users_answer, parent, false);
        return new UsersAnswersAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAnswersAdapter.ViewHolder holder, int position) {
        holder.setItem(questionList.get(position), answers.get(position));

    }

    @Override
    public int getItemCount() {
        if(answers !=null)
            return answers.size();
        else return 0;    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private RandomQuestion question;
        private int answer;
        private TextView questionTV, answer1TV, answer2TV, answer3TV, explanationTV;
        private ImageView check1, check2, check3;
        private ConstraintLayout answer1Layout, answer2Layout, answer3Layout;

        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            linearLayout = itemView;
            questionTV = linearLayout.findViewById(R.id.usersQuestion);
            answer1TV = linearLayout.findViewById(R.id.usersAnswer1);
            answer2TV = linearLayout.findViewById(R.id.usersAnswer2);
            answer3TV = linearLayout.findViewById(R.id.usersAnswer3);
            explanationTV = linearLayout.findViewById(R.id.usersExplanation);
            check1 = linearLayout.findViewById(R.id.imageViewCheck1);
            check2 = linearLayout.findViewById(R.id.imageViewCheck2);
            check3 = linearLayout.findViewById(R.id.imageViewCheck3);
            answer1Layout = linearLayout.findViewById(R.id.answer1Layout);
            answer2Layout = linearLayout.findViewById(R.id.answer2Layout);
            answer3Layout = linearLayout.findViewById(R.id.answer3Layout);
        }

        public void setItem(RandomQuestion question, Integer answer) {
            this.answer = answer;
            this.question = question;

            questionTV.setText(question.getQuestion());
            explanationTV.setText(question.getExplanation());

            if (question.getAnswersList().size()==1){
                answer1TV.setText(question.getAnswersList().get(0));
                answer2Layout.setVisibility(View.GONE);
                answer3Layout.setVisibility(View.GONE);
            }
            else if (question.getAnswersList().size()==2){
                answer1TV.setText(question.getAnswersList().get(0));
                answer2TV.setText(question.getAnswersList().get(1));
                answer3Layout.setVisibility(View.GONE);
            }
            else if (question.getAnswersList().size()==3){
                answer1TV.setText(question.getAnswersList().get(0));
                answer2TV.setText(question.getAnswersList().get(1));
                answer3TV.setText(question.getAnswersList().get(2));
            }
            else {
                answer1Layout.setVisibility(View.GONE);
                answer2Layout.setVisibility(View.GONE);
                answer3Layout.setVisibility(View.GONE);
            }
            showCorrectAnswer(question);
            showChosenAnswer(answer);
        }



        private void showCorrectAnswer(RandomQuestion question){
            switch (question.getCorrect()){
                case 1:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.correct_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    break;
                case 2:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.correct_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    break;
                case 3:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.correct_answer_background));
                    break;
            }
        }

        private void showChosenAnswer(int answer){
            switch (answer){
                case 1:
                    check1.setVisibility(View.VISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    check2.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    check3.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    break;
                default:
                    check1.setVisibility(View.INVISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
            }
        }


    }
}