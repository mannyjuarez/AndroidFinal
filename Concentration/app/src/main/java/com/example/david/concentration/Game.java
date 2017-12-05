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
    //  Card resource ID's are the id's of the ImageView widgets in the xml files
    int[] cardResourceIDs = new int[]{R.id.card_0, R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4, R.id.card_5, R.id.card_6, R.id.card_7, R.id.card_8, R.id.card_9, R.id.card_10, R.id.card_11,
                                R.id.card_12, R.id.card_13, R.id.card_14, R.id.card_15, R.id.card_16, R.id.card_17, R.id.card_18, R.id.card_19};

    //cardArray holds up to 20 ImageViews which represent the cards of the game.
    ImageView[] cardArray = new ImageView[20];

    //Layouts
    //note: index is equal to [(layout difficulty/2) - 2 ]
    int[] layoutResourceIDs = new int[]{R.layout.game_04, R.layout.game_06, R.layout.game_08, R.layout.game_10, R.layout.game_12, R.layout.game_14, R.layout.game_16, R.layout.game_18, R.layout.game_20};

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

        //get the correct resource layout from the layoutResourceID's array by using difficulty to calculate the index.
        int gameLayoutIndex = (difficulty/2) - 2;

        //inflate the layout
        View v;
        v = inflater.inflate(layoutResourceIDs[gameLayoutIndex], container, false);

        //Set the score
        textView = (TextView) v.findViewById(R.id.textView);
        textView.setText("Score: " + Integer.toString(score));

        //Fill up the cardArray with ImageView widgets from the layout and give those imageViews a tag.
        for(int i = 0; i < difficulty; i++){
            cardArray[i] = (ImageView) v.findViewById(cardResourceIDs[i]);
            cardArray[i].setTag(Integer.toString(i));
        }

        //Create board and set listeners based on difficulty
        createBoard(difficulty);
        setListeners(difficulty);

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
                cardArray[i].setEnabled(false);
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
                if(correct.indexOf(i) == -1) cardArray[i].setEnabled(true);
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
                if(correct.indexOf(firstIndex) == -1) cardArray[firstIndex].setImageResource(R.drawable.back);
                if(correct.indexOf(secondIndex) == -1) cardArray[secondIndex].setImageResource(R.drawable.back);

                //Then re-enable cards not on the "correct" list
                for(int i = 0; i <difficulty; i++)
                {
                    if(correct.indexOf(i) == -1) cardArray[i].setEnabled(true);
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
                    drawImage(cardArray[i], deck.get(i));
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

    /*
        this methods sets the appropriate ammount of listeners based on the difficulty of the game
     */
    private void setListeners(int difficulty){
        switch (difficulty){
            case 20:
                cardArray[19].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[19], (String)view.getTag());
                    }
                });

                cardArray[18].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[18], (String)view.getTag());
                    }
                });
            case 18:
                cardArray[17].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[17], (String)view.getTag());
                    }
                });

                cardArray[16].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[16], (String)view.getTag());
                    }
                });
            case 16:
                cardArray[15].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[15], (String)view.getTag());
                    }
                });

                cardArray[14].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[14], (String)view.getTag());
                    }
                });
            case 14:
                cardArray[13].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[13], (String)view.getTag());
                    }
                });

                cardArray[12].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[12], (String)view.getTag());
                    }
                });
            case 12:
                cardArray[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[11], (String)view.getTag());
                    }
                });

                cardArray[10].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[10], (String)view.getTag());
                    }
                });
            case 10:
                cardArray[9].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[9], (String)view.getTag());
                    }
                });

                cardArray[8].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[8], (String)view.getTag());
                    }
                });
            case 8:
                cardArray[7].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[7], (String)view.getTag());
                    }
                });

                cardArray[6].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[6], (String)view.getTag());
                    }
                });
            case 6:
                cardArray[5].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[5], (String)view.getTag());
                    }
                });

                cardArray[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[4], (String)view.getTag());
                    }
                });
            default:
                cardArray[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[3], (String)view.getTag());
                    }
                });

                cardArray[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[2], (String)view.getTag());
                    }
                });

                cardArray[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[1], (String)view.getTag());
                    }
                });

                cardArray[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(cardArray[0], (String)view.getTag());
                    }
                });
                break;
        }
    }
}

