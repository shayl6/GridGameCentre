# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.


## Contact information: 
* [
Shang Liu, shaun.liu@mail.utoronto.ca,6265664438;
Runqi Bi, runqi.bi@mail.utoronto.ca, 6476767926;
Qi Zou, qi.zou@mail.utoronto.ca, 4168273760;
Quan Xu, quan.xu@mail.utoronto.ca, 6476740007;
Zijin Zhang, zijin.zhang@mail.utoronto.ca, 6476760331]


### Real name vs. Git name:
* [
Shang Liu: Shaun Liu,
Runqi Bi: birunqi,
Qi Zou: zouqi,
Quan Xu: quan,
Zijin Zhang: Zijin Zhang & Arieski.
]


### communication tools: [Wechat. We have a Wechat group to communicate.]


### Team contact:
* [ 
I will get my allotted work done on time.
I will attend every team meeting and actively participate.
Should an emergency arise that prevents me from attending a team meeting, I will notify my team immediately.
The work will be divided roughly equally among all team members.
I will help my team to understand every concept in the project.
If I do not understand a concept or code, I will immediately ask my team for help.
Shang Liu agreed.
Runqi Bi agreed.
Qi Zou agreed.
Quan Xu agreed.
Zijin Zhang agreed
]


### Detail:
[We will do it in pairs, since one person can be typing and the other can be looking up 
documentation, and one can discuss design issues as the arise.). If in some small cases some codes 
need to be done individually, we will do it individually and finish it on time.]


### Contributes(generally) in project phase 2:
* [
Note: Since we are a group, so sometimes we group together to write codes(some overlap 
coding). Then the contributes below is only a general contributes. Actually each group member 
did much more than the contribute list below.
\\
Shang Liu: interfaces, complexity，sliding tile(board unsolvable), factory design pattern, ST unit tests.
Runqi Bi: game color matching(tile, board, board manager, gameActivity) and Superclass
Qi Zou: game color matching(record, scoreboard, scoreboardActivity), README.md, CM unit tests.
Quan Xu: game Flip to Win (tile, board, board manager, gameActivity, scoreboard) and Superclass, FTW unit tests.
Zijin Zhang: Data manager, file manager(load and save), and Superclass, unit tests for Score file and Users file.
]


### Meeting notes:
[
Meeting No.1 (Oct.22)
We did the CRC of phase 1, and we draw 5 different cards to show the connections between each 
classes. We also set up our phase 1 repository successfully.
Meeting No.2 (Oct.24)
We finish the README.md and TEAM.md files in tut.
//
Meeting No.2 (Oct.29)
We finish the design part of the project. We finish the CRC and uml of this project together.
And we separated the project into 5 main part: User-UserManager, Game-GameManager, Activities, 
Scoreboards and a MainClass. Runqi Bi writes User-UserManager, Shang Liu and Qi Zou writes 
Activities, Quan Xu does Scoreboards and Zijin Zhang does Game-GameManager. We will write 
the MainClass together, since the MainClass is the top class that organize all classes.
In the following days, we are going to finish each part of the project and we are going wo have a 
another meeting on this Saturday.
//
Meeting No.2 (Nov.1)
Today we had some small meetings. Qi Zou and Shang Liu exchange the ideas about how to do 
activities with Runqi Bi, Quan Xu, Zijin Zhang. Then Runqi Bi and Quan Xu discuss the implementation 
of User and UserManager, and come up some new ideas. Quan Xu and Zijin Zhang have argument about the 
logic of Autosave() method, BUT we had agreed on several points regarding how to use AutoSave and 
the structure of AutoSAve() and so on. So our group is dong well right now.
//
Meeting No.3 (Nov.4)
We almost finish every's parts of the projects. All of us had a meeting for the whole day
combining them together, found and solved some existing and potential bugs and errors.
Also, we complete and finalize the login function, save and load function, set complexity
function as well as the score board. Out final design decide to use the Main
class to achieve all operation to the data and objects and we ensure that the Main class is singleton.
By the end of this meeting, we finished most of the feature of the game.
//
Meeting No.4 (Nov.5)
We had another whole-day meeting and we finally complete the development of all functionality
of this game. We tested the undo function, checked if there is any logic error regarding to
the game score and the score board. Furthermore, we redesigned some parts of the user interface
of the game to make it looks clear and nice. Finally, we deleted all the unnecessary code,
fixed the warning and code smells. Additionally, we also developed an game lunch center as well as
the unlimited undo function. For our next task, we well keep working changing the background image
of each tiles and make it possible for user to use their own picture as the background.
\\
Meeting No.4 (Nov.5)
Today is the last day! Right now, we group together and test the game over and over again. And we 
changed the context of the info that shown to the player. We check the correctness of our java doc 
finally. The only thing that is sad is we cannot finish the goal task(changing the background image 
of each tiles and make it possible for user to use their own picture as the background), but we will 
try it again later. For our next task(phase 2), we will working on it as soon as possible. 
\\
Meeting No.6 and No.7 (Nov.15/18)
We met in TUT section, and we discussed about the two new game. Each of us had 2 ideas of the the 
new games. After discussion, we decided that we make two games which were "Color Matching" and 
"Flip to Win". And we separated our group in two minor groups. Qi Zou, Liu Shang, Runqi Bi were
group 1 and Quan Xu, Zijin Zhang were group 2. Group 1 was working the game, 'Color Matching', and 
group 2 was doing the game, 'Flip to Win'.
\\
Meeting No.8 (Nov.21)
We designed the functionality of Color Matching(CM) and Flip to Win(FTW). 
The description of CM is:
CM is a grid where need to unify the color of all the grids. User can change grid colors from 
the top left corner by clicking color buttons at the bottom. The grids user can change would 
increase as you unify more grids. If user undo, user got a penalty of adding 1 extra score. 
The player who took lower steps got higher rank.
The description of FTW is:
TTW is a memory game where you need to match pairs of tiles. Once you click a tile, the front page 
of a tile would be faced up. If user tap two tiles that have different front pages, the two 
tiles would expose for 0.8 seconds. If you flip two identical front page, they got matched 
and will cancel out. The player who took less flips got higher rank.
\\
Meeting No.9 (Nov.24)
Two group of us finished the design of new games and finishing coding the two game. And we have a
problem of update observer. So we watch some youtube videos about the observer and we look through 
some paper and answers about observer. Then we fix the update view issues.
\\
Meeting No.10 (Nov.25)
After we finished the two new game, we redesigned the main class, since in teh feedback of phase 1, 
it suggests we split the main class into two parts. So we redesign it and split main class to two 
class(class DataManager and class FileManager). DataManager use singleton design pattern, and the 
functionality of this class is yto manage date from GameBoardManager and user manager and so on. 
The functionality of FileManager is the to load and save files. So we grouped together and changed 
the main class and applied changes.
\\
Meeting No.11 (Nov.28)
We found a bug of saving/loading file for game Color Matching. The reason of the bug was we create 
new game in the Color Matching GameActivity and since we cannot save/load activity. So we could 
not able to save\load the Color Matching BoardManager. Then, we redesign the structure of game 
Color Matching and made it savable/loadable.
\\
Meeting No.12 (Nov.29)
This the final meeting before submitting. We grouped up together to write the Java Doc and the unit 
test for our controller(which is the C in the MVC design pattern). 
Today is a big day.
\\
Meeting No.13 (Dec.4)
This might be the last meeting for csc207. We finished designing the presentation(outline), and each 
group members will finish their part of ppt, and we will group each part of ppt together later.
]

