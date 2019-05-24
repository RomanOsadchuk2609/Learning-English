package com.dariahaze.learning_english.ui.tests;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.PracticeQuestion;
import com.dariahaze.learning_english.model.PracticeTest;

import java.util.List;

public class PracticeAnswersAdapter extends RecyclerView.Adapter<PracticeAnswersAdapter.ViewHolder> {
    private List<Integer> answers;
    private PracticeTest practiceTest;

    public PracticeAnswersAdapter(List<Integer> answers, PracticeTest practiceTest) {
        this.answers = answers;
        this.practiceTest = practiceTest;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public PracticeTest getPracticeTest() {
        return practiceTest;
    }

    public void setPracticeTest(PracticeTest practiceTest) {
        this.practiceTest = practiceTest;
    }

    @NonNull
    @Override
    public PracticeAnswersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_practice_answer, parent, false);
        return new PracticeAnswersAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PracticeAnswersAdapter.ViewHolder holder, int position) {
        holder.setItem(practiceTest.getQuestions().get(position), answers.get(position));

    }

    @Override
    public int getItemCount() {
        if(answers !=null)
            return answers.size();
        else return 0;    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView questionTV, answer1TV, answer2TV, answer3TV, answer4TV;
        private ImageView check1, check2, check3, check4;
        private ConstraintLayout answer1Layout, answer2Layout, answer3Layout, answer4Layout;

        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            linearLayout = itemView;
            questionTV = linearLayout.findViewById(R.id.practiceQuestion);
            answer1TV = linearLayout.findViewById(R.id.practiceAnswer1);
            answer2TV = linearLayout.findViewById(R.id.practiceAnswer2);
            answer3TV = linearLayout.findViewById(R.id.practiceAnswer3);
            answer4TV = linearLayout.findViewById(R.id.practiceAnswer4);
            check1 = linearLayout.findViewById(R.id.imageViewPracticeCheck1);
            check2 = linearLayout.findViewById(R.id.imageViewPracticeCheck2);
            check3 = linearLayout.findViewById(R.id.imageViewPracticeCheck3);
            check4 = linearLayout.findViewById(R.id.imageViewPracticeCheck4);
            answer1Layout = linearLayout.findViewById(R.id.practiceAnswer1Layout);
            answer2Layout = linearLayout.findViewById(R.id.practiceAnswer2Layout);
            answer3Layout = linearLayout.findViewById(R.id.practiceAnswer3Layout);
            answer4Layout = linearLayout.findViewById(R.id.practiceAnswer4Layout);
        }

        public void setItem(PracticeQuestion question, Integer answer) {

            questionTV.setText(question.getQuestion()+"");

            if (question.getAmountOfAnswers()==2){
                answer1TV.setText(question.getAnswer1());
                answer2TV.setText(question.getAnswer2());
                answer3Layout.setVisibility(View.GONE);
                answer4Layout.setVisibility(View.GONE);
            }
            else if (question.getAmountOfAnswers()==3){
                answer1TV.setText(question.getAnswer1());
                answer2TV.setText(question.getAnswer2());
                answer3TV.setText(question.getAnswer3());
                answer4Layout.setVisibility(View.GONE);
            }
            else if (question.getAmountOfAnswers()==4){
                answer1TV.setText(question.getAnswer1());
                answer2TV.setText(question.getAnswer2());
                answer3TV.setText(question.getAnswer3());
                answer4TV.setText(question.getAnswer4());
            }
            else {
                answer1Layout.setVisibility(View.GONE);
                answer2Layout.setVisibility(View.GONE);
                answer3Layout.setVisibility(View.GONE);
                answer4Layout.setVisibility(View.GONE);
            }
            showCorrectAnswer(question);
            showChosenAnswer(answer);
        }



        private void showCorrectAnswer(PracticeQuestion question){
            switch (question.getCorrect()){
                case 1:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.correct_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer4TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    break;
                case 2:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.correct_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer4TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    break;
                case 3:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.correct_answer_background));
                    answer4TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    break;
                case 4:
                    answer1TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer2TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer3TV.setBackground(linearLayout.getContext()
                            .getDrawable(R.drawable.wrong_answer_background));
                    answer4TV.setBackground(linearLayout.getContext()
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
                    check4.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    check2.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
                    check4.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    check3.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    check4.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    check4.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
                    break;
                default:
                    check1.setVisibility(View.INVISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
                    check4.setVisibility(View.INVISIBLE);
            }
        }


    }
}