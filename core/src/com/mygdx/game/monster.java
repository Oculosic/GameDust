public class monster{
    if (playerXPos > monsterXPos - 10 && playerXPos < monsterXPos + 10 && playerYPos > monsterYPos - 10 && playerYPos < monsterYPos + 10){
        monsterXPos -= playerXPos / 5
        monsterYPos -= playerYPos / 5
    }
}