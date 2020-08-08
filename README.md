Introduction:
	Simple game engine just for fun, hello world on java

Documentation:
	
	Osas:
		ShowMenu();

	OsasWindow:
		//Create Window
		static OsasWindow osas = new OsasWindow(frame);

		//Show Window
		osas.ShowWindow();

		//Set Background(file name)
		osas.SetBackground("res/0_B2.png");

		//Show Message Box (text)
		osas.MessageBox("You lose, when open this game. UHaaaahaa");

		//This stage
		int NextStage = osas.ThisStage + 1

		//Save Number of stage, if no number => new game, example: SaveOrNewF();
		osas.SaveOrNewF(NextStage);

		//Show Game Message
		osas.ShowGameMessage("Little Mauro: i am reaady");

		//Hide Game Message
		osas.HideGameMessage();

		//Wait 2 seconds
		osas.Wait(2);

	OsasObject:

		//Create object
		OsasObject stone = new OsasObject(frame, 100, 100);//(frame, x, y)

		//Chose image for object
		stone.SetObjectImage("res/stone.png");

		//Get x, y , width, height of object
		int xtest = stone.x;
		int ytest = stone.y
		int wtest = stone.width
		int htest = stone.height

		//Move Object
		stone.MoveObject(stone.x+10, stone.y);//Move right
		stone.MoveObject(stone.x-10, stone.y);//Move left
		stone.MoveObject(stone.x, stone.y+10);//Move up

		//Delete Object
		stone.DestroyObject();

	OsasSound:
		//Create sound1(file)
		OsasSound sound1 = new OsasSound(new File("res\\Saver_v0.WAV"));

		//Stop sound1, maybe its playing now
		sound1.stop();

		//Play sound1
		sound1.play();

	Java:
		// Multiprocces, u edno vrema chet radit dva proccesa

		//Example: Jump:

		LittleMauro.MoveObject(LittleMauro.x, LittleMauro.y-50);//go up
		new Thread(new Runnable() { 
           	public void run() {
		        osas.Wait(1); // wait 1 second
		        LittleMauro.MoveObject(LittleMauro.x, LittleMauro.y+50);//go down
			}
		}).start();