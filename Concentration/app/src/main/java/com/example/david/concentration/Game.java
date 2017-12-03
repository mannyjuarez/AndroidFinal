package com.example.david.concentration;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**s
 * Created by David on 12/2/2017.
 */

public class Game extends android.support.v4.app.Fragment{

    //Get game difficulty
    private int difficulty;
    public static Game newInstance(int difficulty) {
        Game fragment = new Game();
        fragment.difficulty = difficulty;
        return fragment;
    }

    //Score
    TextView textView;

    //Cards
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16, iv17, iv18, iv19, iv20;
    ImageView[] imageArray = new ImageView[] {iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16, iv17, iv18, iv19, iv20};

    //"deck" when creating board
    ArrayList<String> deck = new ArrayList<String>();
    
    //"correct" are cards setEnabled(false)
    ArrayList<Integer> correct = new ArrayList<Integer>();

    //Game play
    boolean firstClick = true;
    String firstAnimal = "";
    String secondAnimal = "";
    int firstIndex = 0;
    int secondIndex = 0;
    int score = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        /*
        if(difficulty == 4) {
            view = inflater.inflate(R.layout.x4_frag, container, false);
        } else if(difficulty == 6) {
            view = inflater.inflate(R.layout.x6_frag, container, false);
        } else if(difficulty == 8) {
            view = inflater.inflate(R.layout.x8_frag, container, false);
        } else if(difficulty == 10) {
            view = inflater.inflate(R.layout.x10_frag, container, false);
        } else if(difficulty == 12) {
            view = inflater.inflate(R.layout.x12_frag, container, false);
        } else if(difficulty == 14) {
            view = inflater.inflate(R.layout.x14_frag, container, false);
        } else if(difficulty == 16) {
            view = inflater.inflate(R.layout.x16_frag, container, false);
        } else if(difficulty == 18) {
            view = inflater.inflate(R.layout.x18_frag, container, false);
        } else {
            view = inflater.inflate(R.layout.F20, container, false);
        }
        */

        v = inflater.inflate(R.layout.f20, container, false);

        //Set the score
        textView = (TextView) v.findViewById(R.id.textView);
        textView.setText("Score: " + Integer.toString(score));

        iv1 = (ImageView) v.findViewById(R.id.iv1);
        iv2 = (ImageView) v.findViewById(R.id.iv2);
        iv3 = (ImageView) v.findViewById(R.id.iv3);
        iv4 = (ImageView) v.findViewById(R.id.iv4);
        iv5 = (ImageView) v.findViewById(R.id.iv5);
        iv6 = (ImageView) v.findViewById(R.id.iv6);
        iv7 = (ImageView) v.findViewById(R.id.iv7);
        iv8 = (ImageView) v.findViewById(R.id.iv8);
        iv9 = (ImageView) v.findViewById(R.id.iv9);
        iv10 = (ImageView) v.findViewById(R.id.iv10);
        iv11 = (ImageView) v.findViewById(R.id.iv11);
        iv12 = (ImageView) v.findViewById(R.id.iv12);
        iv13 = (ImageView) v.findViewById(R.id.iv13);
        iv14 = (ImageView) v.findViewById(R.id.iv14);
        iv15 = (ImageView) v.findViewById(R.id.iv15);
        iv16 = (ImageView) v.findViewById(R.id.iv16);
        iv17 = (ImageView) v.findViewById(R.id.iv17);
        iv18 = (ImageView) v.findViewById(R.id.iv18);
        iv19 = (ImageView) v.findViewById(R.id.iv19);
        iv20 = (ImageView) v.findViewById(R.id.iv20);

        imageArray[0] = iv1;
        imageArray[1] = iv2;
        imageArray[2] = iv3;
        imageArray[3] = iv4;
        imageArray[4] = iv5;
        imageArray[5] = iv6;
        imageArray[6] = iv7;
        imageArray[7] = iv8;
        imageArray[8] = iv9;
        imageArray[9] = iv10;
        imageArray[10] = iv11;
        imageArray[11] = iv12;
        imageArray[12] = iv13;
        imageArray[13] = iv14;
        imageArray[14] = iv15;
        imageArray[15] = iv16;
        imageArray[16] = iv17;
        imageArray[17] = iv18;
        imageArray[18] = iv19;
        imageArray[19] = iv20;

        for(int i = 0; i < difficulty; i++)
        {
            imageArray[i].setTag(Integer.toString(i));
        }

        //Create board based on difficulty
        createBoard(difficulty);



        //Set up listeners
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv1, (String)view.getTag());
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv2, (String)view.getTag());
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv3, (String)view.getTag());
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv4, (String)view.getTag());
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv5, (String)view.getTag());
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv6, (String)view.getTag());
            }
        });

        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv7, (String)view.getTag());
            }
        });

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv8, (String)view.getTag());
            }
        });

        iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv9, (String)view.getTag());
            }
        });

        iv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv10, (String)view.getTag());
            }
        });

        iv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv11, (String)view.getTag());
            }
        });

        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv12, (String)view.getTag());
            }
        });

        iv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv13, (String)view.getTag());
            }
        });

        iv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv14, (String)view.getTag());
            }
        });

        iv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv15, (String)view.getTag());
            }
        });

        iv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv16, (String)view.getTag());
            }
        });

        iv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv17, (String)view.getTag());
            }
        });

        iv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv18, (String)view.getTag());
            }
        });

        iv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv19, (String)view.getTag());
            }
        });

        iv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(iv20, (String)view.getTag());
            }
        });
        return v;
    }

    //Flip the card and store the first click and second click
    private void flipCard(ImageView iv, String card){
        //Get image for selected card
        int index = Integer.parseInt(card);
        drawImage(iv, deck.get(index));

        //Store the first and second index and animal name
        if(firstClick == true){
            firstIndex = index;
            firstAnimal = deck.get(index);
            firstClick = false;
            iv.setEnabled(false);
        }
        else if(firstClick == false){
            secondIndex = index;
            secondAnimal = deck.get(index);
            firstClick = true;

            //Turn off all cards
            for(int i = 0; i < difficulty; i++)
            {
                imageArray[i].setEnabled(false);
            }

            //Check if the selected images are equal
            compareCards();
        }
    }


    private void compareCards()
    {
        //If correct:
        if(firstAnimal.equals(secondAnimal)) {
            //1) Store the index of correct cards
            correct.add(firstIndex);
            correct.add(secondIndex);

            //2) Calculate the score
            score += 2;

            //3) Re-enable cards not on the "correct" list
            for(int i = 0; i < difficulty; i++)
            {
                if(correct.indexOf(i) == -1) imageArray[i].setEnabled(true);
            }
        }

        //If incorrect:
        else{
            //Subtract score
            if(score != 0) score--;
        }

        //Update "Score:" text view
        textView.setText("Score: " + Integer.toString(score));

        //Check if game is over
        //isGameOver();
    }

    private void isGameOver(){
        //Probably do High Scores here too
    }

    private void createBoard(int difficulty){
        //String array of different card options
        String[] cards = {"beaver", "giraffe", "goat", "hippo", "owl", "peacock", "penguin", "turkey", "squirrel", "tri"};

        //Shuffle "cards" array and pick half of difficulty
        Collections.shuffle(Arrays.asList(cards));

        for (int i = 0; i < difficulty / 2; i++)
        {
            deck.add(cards[i]);
            deck.add(cards[i]);
        }

        //Shuffle again
        Collections.shuffle(deck);
    }

    //Set picture based on name to ImageViewer
    private void drawImage(ImageView iv, String name)
    {
        iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getActivity().getPackageName())));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //"Try Again" button
        ((Button)getActivity().findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Flip back over incorrect cards
                if(correct.indexOf(firstIndex) == -1) imageArray[firstIndex].setImageResource(R.drawable.back);
                if(correct.indexOf(secondIndex) == -1) imageArray[secondIndex].setImageResource(R.drawable.back);

                //Then re-enable cards not on the "correct" list
                for(int i = 0; i <difficulty; i++)
                {
                    if(correct.indexOf(i) == -1)imageArray[i].setEnabled(true);
                }
            }
        });


        //"New Game" button
        ((Button)getActivity().findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Play.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        //"End Game" button
        ((Button)getActivity().findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Flip over all cards
                for(int i = 0; i < difficulty; i++)
                {
                    drawImage(imageArray[i], deck.get(i));
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, Home.newInstance())
                                .addToBackStack(null)
                                .commit();
                    }
                }, 3000);
            }

        });
    }
}
