package Game;

import java.util.*;

import Game.GamePanel.escape;

import java.lang.System.Logger.Level;
import java.math.*;


// 게임 플레이 상호작용 클래스
public class dataClass {
	
	// 어디 입구로 들어갔는지 확인하는 변수 | -1 : 초기 상태 
	int gate = -1;
	posArray ps = new posArray();
	
	static int missions[];
	
	public dataClass() { }
	// 이 위치(파랑색 선)에 닿았을 때 어떤 입구(파랑색 선)인지 확인
	void teleport_map(int mapNum, int x, int y) {
		this.gate = -1;
		switch (mapNum){
		case 0: {
			if(  ( y == 0 ) && ( checkPosition( 450, x, 500, 1 ) )||
					( ( x == Game.GamePanel.xMax ) && ( checkPosition(0, y, 50, 1) ) ) ) { this.gate = 0; }
			else if( ( x == 0 ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 0, y, 50, 1 ) ) ||
					(y == 0) && ( checkPosition(0, x, 100, 1) ) ) { this.gate = 2; }
			break;
		}
		case 1: {
			if( ( checkPosition( 150, x, 350, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == 0  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 2; }
			break;
		}
		case 2:{
			if(  ( checkPosition( 350, x, 450, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == 0  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 50, x, 100, 1 ) ) && ( y == 0 ) ) { this.gate = 3; }
			break;
		}
		case 3:{
			if(  ( checkPosition( 300, x, 400, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == 0  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 100, x, 200, 1 ) ) && ( y == 0 ) ) { this.gate = 3; }
			break;
		}
		case 4:{
			if(  ( checkPosition( 350, x, 450, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == 0  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 50, x, 150, 1 ) ) && ( y == 0 ) ) { this.gate = 3; }
			break;
		}
		case 5:{
			if(  ( checkPosition( 300, x, 400, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( checkPosition( 300, x, 400, 1 ) ) && ( y == Game.GamePanel.yMax )  ) { this.gate = 2; }
			break;
		}
		case 6:{
			if(  ( checkPosition( 300, x, 400, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 100, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 450, y, 500, 1 ) )  ) { this.gate = 2; }
			else if( ( checkPosition( 300, x, 400, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0  ) && ( checkPosition( 450, y, 500, 1 ) ) ) { this.gate = 4; }
			else if( ( x == 0  ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 5; }
			break;
		}
		case 7:{
			if(  ( checkPosition( 300, x, 400, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 0, y, 100, 1 ) )  ||
					 ( checkPosition( 450, x, 500, 1 ) ) && ( y == 0 )) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 200, y, 300, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 300, x, 400, 1 ) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0  ) && ( checkPosition( 50, y, 250, 1 ) ) ) { this.gate = 4; }
			break;
		}
		case 8:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 450, x, 500, 1 ) ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 50, y, 150, 1 ) )  ) { this.gate = 2; }
			break;
		}
		case 9:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 200, x, 300, 1 ) ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 50, y, 150, 1 ) )  ) { this.gate = 2; }
			break;
		}
		case 10:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 250, x, 350, 1 ) ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			break;
		}
		case 11:{
			if(  (checkPosition( 250, x, 350, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( checkPosition( 400, x, 500, 1 ) ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( checkPosition( 250, x, 350, 1 ) ) && ( y == Game.GamePanel.yMax )  ) { this.gate = 2; }
			break;
		}
		case 12:{
			if(  (checkPosition( 250, x, 400, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax ) && ( checkPosition( 0, y, 50, 1 )) ) { this.gate = 1; }
			break;
		}
		case 13:{
			if(  ( checkPosition( 350, x, 450, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( checkPosition( 350, x, 500, 1 )  ) && ( y== Game.GamePanel.yMax) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 250, y, 300, 1 ) )  ) { this.gate = 2; }
			else if( ( x == 0 ) && ( checkPosition( 150, y, 200, 1 ) ) ) { this.gate = 3; }
			break;
		}
		case 14:{
			if(  (checkPosition( 150, x, 250, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			break;
		}
		case 15:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 1; }
			else if( ( checkPosition( 0, x, 50, 1 ) ) && ( y == Game.GamePanel.yMax )  ) { this.gate = 2; }
			break;
		}
		case 16:{
			if(  (  checkPosition( 0, x, 50, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 450, y, 500, 1 ) ) ) { this.gate = 1; }
			break;
		}
		case 17:{
			if( ( checkPosition( 400, x, 500, 1 )  ) && ( y == 0) ) { this.gate = 0; }
			else if( ( x == 0 ) && ( checkPosition( 400, y, 500, 1 ) )  ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 100, y, 200, 1 ) ) ) { this.gate = 2; }
			else if(  ( x == 0 ) && ( checkPosition( 0, y, 50, 1 ) ) ) { this.gate = 3; }
			break;
		}
		case 18:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && (  checkPosition( 350, y, 450, 1 ) ) ) { this.gate = 1; }
			break;
		}
		case 19:{
			if(  ( checkPosition( 350, x, 450, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			break;
		}
		case 20:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 300, y, 400, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 50, x, 150, 1 )  ) && ( y == Game.GamePanel.yMax) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 300, y, 400, 1 ) )  ) { this.gate = 2; }
			else if( ( x == 0 ) && ( checkPosition( 0, y, 50, 1 ) ) ) { this.gate = 3; }
			break;
		}
		case 21:{
			if(  ( checkPosition( 350, x, 450, 1 ) ) && (  y == Game.GamePanel.yMax)  ) { this.gate = 0; }
			else if( ( x == 0  ) && ( checkPosition( 300, y, 400, 1 ) ) ) { this.gate = 1; }
			break;
		}
		case 22:{
			if(  ( x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 50, x, 150, 1 )  ) && ( y == Game.GamePanel.yMax) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 350, y, 450, 1 ) )  ) { this.gate = 2; }
			else if( (checkPosition( 50, x, 150, 1 )  ) && ( y == 0 ) ) { this.gate = 3; }
			break;
		}
		case 23:{
			if(  ( checkPosition( 200, x, 300, 1 ) ) && ( y == 0) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 0, y, 100, 1 ) ) ) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 200, x, 300, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0 ) && ( checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 4; }
			else if( ( x == 0 ) && ( checkPosition( 150, y, 200, 1 ) ) ) { this.gate = 5; }
			else if( ( x == 0 ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 6; }
			break;
		}
		case 24:{
			if(  ( checkPosition( 200, x, 300, 1 ) ) && ( y == 0) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 0, y, 200, 1 ) ) ) { this.gate = 1; }
			else if( ( checkPosition( 200, x, 300, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 2; }
			break;
		}
		case 25:{
			if(  ( checkPosition( 0, x, 500, 1 ) ) && ( y == 0) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax - 50  ) && (checkPosition( 400, y, 500, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 250, x, 400, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0 ) && ( checkPosition( 400, y, 500, 1 ) ) ) { this.gate = 4; }
			else if( ( x == 0 ) && ( checkPosition( 50, y, 150, 1 ) ) ) { this.gate = 5; }
			break;
		}
		case 26:{
			if(  (  x == 0 ) && ( checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 1; }
			break;
		}
		case 27:{
			if(  ( checkPosition( 150, x, 350, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( checkPosition( 200, x, 300, 1 )  ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0  ) && (checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 2; }
			break;
		}
		case 28:{
			if( ( checkPosition( 200, x, 300, 1 )  ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 0; }
			else if(  ( x == 0 ) && ( checkPosition(50, y, 150, 1) ) ) { this.gate = 1; }
			break;
		}
		case 29:{
			if(  ( checkPosition( 250, x, 350, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( checkPosition( 250, x, 350, 1 )  ) && (  y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0  ) && (checkPosition( 300, y, 400, 1 ) ) ) { this.gate = 2; }
			break;
		}	
		case 30:{
			if(  ( checkPosition( 250, x, 350, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( y == Game.GamePanel.yMax) && (checkPosition(150, x, 300, 1) ) ) { this.gate = 1; }
			else if( ( x == 0  ) && (checkPosition( 100, y, 200, 1 ) ) ) { this.gate = 2; }
			break;
		}
		case 31:{
			if(  ( checkPosition( 50, x, 150, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 250, y, 350, 1 ) ) ) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax   ) && (checkPosition( 400, y, 500, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 50, x, 150, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0 ) && ( checkPosition( 100, y, 250, 1 ) ) ) { this.gate = 4; }
			break;
		}
		case 32:{
			if(  ( checkPosition( 300, x, 400, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( checkPosition( 300, x, 400, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 350, y, 400, 1 ) ) ) { this.gate = 2; }
			else if( ( x == 0 ) && ( checkPosition( 200, y, 300, 1 ) ) ) { this.gate = 3; }
			break;
		}
		case 33:{
			if(  ( checkPosition( 450, x, 500, 1 ) ) && ( y == 0) || 
				 ( x == Game.GamePanel.xMax ) && ( checkPosition( 0, y, 50, 1 ) ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 200, y, 250, 1 ) ) ) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax  ) && ( checkPosition( 300, y, 400, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 200, x, 300, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0 ) && ( checkPosition( 300, y, 400, 1 ) ) ) { this.gate = 4; }
			else if( ( checkPosition(150, x, 250, 1) ) && ( y == 0 ) ) { this.gate = 5; }
			break;
		}
		case 34:{
			if(  ( checkPosition( 350, x, 450, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( checkPosition( 350, x, 450, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 200, y, 300, 1 ) ) ) { this.gate = 2; }
			break;
		}
		case 35:{
			if(  (  x == Game.GamePanel.xMax ) && ( checkPosition( 200, y, 300, 1 ) ) ) { this.gate = 0; }
			else if( ( x == 0  ) && ( checkPosition( 200, y, 300, 1 ) ) ) { this.gate = 1; }
			break;
		}
		case 36:{
			if(  (  x == Game.GamePanel.xMax ) && ( checkPosition( 350, y, 450, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 200, x, 250, 1 )  ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( x == 0 ) && ( checkPosition( 150, y, 250, 1 ) ) ) { this.gate = 2; }
			break;
		}
		case 37:{
			if(  ( checkPosition( 200, x, 250, 1 ) ) && ( y == 0 ) ) { this.gate = 0; }
			else if( ( x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 100, 1 ) ) ) { this.gate = 1; }
			else if( ( x == Game.GamePanel.xMax ) && ( checkPosition( 400, y, 450, 1 ) ) ) { this.gate = 2; }
			else if( ( checkPosition( 200, x, 300, 1 ) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( checkPosition( 0, x, 50, 1 ) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 4; }
			else if( ( x == 0 ) && ( checkPosition(50, y, 150, 1) ) ) { this.gate = 5; }
			break;
		}
		case 38:{
			if(  (  x == Game.GamePanel.xMax ) && ( checkPosition( 50, y, 100, 1 ) ) ) { this.gate = 0; }
			else if( ( checkPosition( 450, x, 500, 1) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 1; }
			else if( ( checkPosition( 200, x, 300, 1) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 2; }
			else if( ( checkPosition( 0, x, 50, 1 ) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 3; }
			else if( ( x == 0 ) && ( checkPosition(50, y, 100, 1) ) ) { this.gate = 4; }
			break;
		}
		case 39:{
			if( ( checkPosition( 0, x, 500, 1) ) && ( y == Game.GamePanel.yMax ) ) { this.gate = 0; }
			break;
		}
		case 40:{
			if( ( x == Game.GamePanel.xMax ) && ( checkPosition( 0, y, 100, 1 ) ) ) { this.gate = 0; }
			else if( ( x == 0 ) && ( checkPosition( 0, y, 100, 1 ) ) ) { this.gate = 1; }
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + mapNum);
		}
		
		// 위치를 확인 후 헤당하는 다음 맵으로 이동
		if(this.gate != -1) {
			Game.GamePanel.x = ps.tele[mapNum][this.gate][0];
			Game.GamePanel.y = ps.tele[mapNum][this.gate][1];
			Game.GamePanel.mapNum = ps.tele[mapNum][this.gate][2];
		}
	}
	// 내가 갈 수 없는 영역인지 확인 | true : 갈 수 있는 지역 , false : 갈 수 없는 지역
	boolean blockSpace(int mapNum, int x, int y) {
		boolean check = true;
		switch (mapNum){
		case 0: {
			if(  	( checkPosition(90, x, 450, 1) && checkPosition(0, y, 100, 1) )		|| 
					( checkPosition(0, x, 300, 1) && checkPosition(290, y, 500, 1) ) 		) check = false;
			break;
		}
		case 1:{
			if(  	( checkPosition(0, x, 150, 1) && checkPosition(0, y, 100, 1) ) 		|| 
					( checkPosition(340, x, 500, 1) && checkPosition(0, y, 100, 1) ) 	|| 
					( checkPosition(0, x, 500, 1) && checkPosition(340, y, 500, 1) ) 	|| 
					( checkPosition(140, x, 500, 1) && checkPosition(290, y, 400, 1) )   	) check = false;
			break;	
		}
		case 2:{
			if(  	( checkPosition(0, x, 50, 1) && checkPosition(0, y, 100, 1) ) 		|| 
					( checkPosition(90, x, 350, 1) && checkPosition(0, y, 100, 1) ) 	|| 
				  	( checkPosition(440, x, 500, 1) && checkPosition(0, y, 100, 1) ) 	|| 
				  	( checkPosition(0, x, 500, 1) && checkPosition(240, y, 500, 1) )   		) check = false;
			break;
		}
		case 3:{
			if(  	( checkPosition(0, x, 100, 1) && checkPosition(0, y, 100, 1) ) 		|| 
					( checkPosition(190, x, 300, 1) && checkPosition(0, y, 100, 1) ) 	|| 
					( checkPosition(390, x, 500, 1) && checkPosition(0, y, 100, 1) ) 	|| 
					( checkPosition(0, x, 400, 1) && checkPosition(240, y, 500, 1) )   		) check = false;
			break;
		}
		case 4:{
			if(  	( checkPosition(0, x, 50, 1) && checkPosition(0, y, 100, 1) ) 		|| 
					( checkPosition(140, x, 350, 1) && checkPosition(0, y, 100, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(0, y, 100, 1) ) 	|| 
					( checkPosition(0, x, 500, 1) && checkPosition(240, y, 500, 1) )   		) check = false;
			break;
		}
		case 5:{
			if(  	( checkPosition(0, x, 300, 1) && checkPosition(0, y, 500, 1) ) 		|| 
					( checkPosition(440, x, 500, 1) && checkPosition(0, y, 100, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(290, y, 500, 1) )		) check = false;
			break;
		}
		case 6:{
			if(  	( checkPosition(0, x, 300, 1) && checkPosition(0, y, 150, 1) ) 		|| 
					( checkPosition(440, x, 500, 1) && checkPosition(0, y, 50, 1) ) 	|| 
					( checkPosition(0, x, 300, 1) && checkPosition(240, y, 400, 1) ) 	|| 
					( checkPosition(90, x, 300, 1) && checkPosition(400, y, 450, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(240, y, 450, 1) )   	) check = false;
			break;
		}
		case 7:{
			if(  	( checkPosition(290, x, 400, 1) && checkPosition(90, y, 200, 1) ) 	|| 
					( checkPosition(0, x, 250, 1) && checkPosition(290, y, 500, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(340, y, 500, 1) ) 		) check = false;
			break;
		}
		case 8:{
			if(  	( checkPosition(0, x, 100, 1) && checkPosition(190, y, 300, 1) ) 	|| 
					( checkPosition(390, x, 400, 1) && checkPosition(190, y, 400, 1) ) 	|| 
					( checkPosition(0, x, 400, 1) && checkPosition(290, y, 500, 1) ) 		) check = false;
			break;
		}
		case 9:{
			if(  	( checkPosition(0, x, 150, 1) && checkPosition(190, y, 500, 1) ) 	|| 
					( checkPosition(390, x, 500, 1) && checkPosition(190, y, 500, 1) ) 		) check = false;
			break;
		}
		case 10:{
			if(  	( checkPosition(0, x, 200, 1) && checkPosition(190, y, 500, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(190, y, 500, 1) ) 		) check = false;
			
			break;
		}
		case 11:{
			if(  	( checkPosition(0, x, 200, 1) && checkPosition(0, y, 500, 1) ) 		|| 
					( checkPosition(200, x, 250, 1) && checkPosition(390, y, 500, 1) ) 	||
					( checkPosition(340, x, 500, 1) && checkPosition(0, y, 400, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(390, y, 450, 1) ) 	||
					( checkPosition(340, x, 400, 1) && checkPosition(440, y, 500, 1) ) 		) check = false;
			break;
		}
		case 12:{
			if(  	( checkPosition(0, x, 250, 1) && checkPosition(0, y, 50, 1) ) 		|| 
					( checkPosition(0, x, 300, 1) && checkPosition(90, y, 250, 1) ) 	|| 
					( checkPosition(0, x, 300, 1) && checkPosition(340, y, 500, 1) ) 	||
					( checkPosition(390, x, 500, 1) && checkPosition(40, y, 500, 1) ) 		) check = false;
			break;
		}
		case 13:{
			if(  	( checkPosition(0, x, 300, 1) && checkPosition(0, y, 50, 1) ) 		||
					( checkPosition(0, x, 100, 1) && checkPosition(40, y, 150, 1) ) 	|| 
					( checkPosition(0, x, 300, 1) && checkPosition(290, y, 500, 1) ) 		) check = false;
			break;
		}
		case 14:{
			if(  	( checkPosition(0, x, 100, 1) && checkPosition(0, y, 500, 1) ) 		||
					( checkPosition(140, x, 300, 1) && checkPosition(190, y, 500, 1) ) 	|| 
					( checkPosition(340, x, 450, 1) && checkPosition(190, y, 500, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(390, y, 500, 1) ) 		) check = false;
			break;
		}
		case 15:{
			if(  	( checkPosition(0, x, 100, 1) && checkPosition(0, y, 200, 1) ) 		||
					( checkPosition(140, x, 500, 1) && checkPosition(0, y, 150, 1) ) 	|| 
					( checkPosition(190, x, 300, 1) && checkPosition(240, y, 300, 1) ) 	|| 
					( checkPosition(340, x, 500, 1) && checkPosition(240, y, 300, 1) )	||
					( checkPosition(390, x, 500, 1) && checkPosition(290, y, 400, 1) ) 	|| 
					( checkPosition(190, x, 250, 1) && checkPosition(300, y, 450, 1) ) 	|| 
					( checkPosition(40, x, 150, 1) && checkPosition(340, y, 450, 1) ) 	||
					( checkPosition(40, x, 500, 1) && checkPosition(440, y, 500, 1) ) 		) check = false;
			
			break;
		}
		case 16:{
			if(  	( checkPosition(40, x, 500, 1) && checkPosition(0, y, 200, 1) ) 	||
					( checkPosition(390, x, 500, 1) && checkPosition(190, y, 450, 1) ) 	|| 
					( checkPosition(0, x, 300, 1) && checkPosition(290, y, 500, 1) ) 		) check = false;
			break;
		}
		case 17:{
			if(  	( checkPosition(0, x, 350, 1) && checkPosition(40, y, 100, 1) ) 	||
					( checkPosition(0, x, 350, 1) && checkPosition(190, y, 400, 1) ) 		) check = false;
			break;
		}
		case 18:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 50, 1) ) 		||
					( checkPosition(0, x, 200, 1) && checkPosition(40, y, 450, 1) ) 	|| 
					( checkPosition(390, x, 500, 1) && checkPosition(140, y, 350, 1) ) 	|| 
					( checkPosition(0, x, 500, 1) && checkPosition(440, y, 500, 1) ) 		) check = false;
			break;
		}
		case 19:{
			if(  	( checkPosition(0, x, 350, 1) && checkPosition(0, y, 300, 1) ) 		||
					( checkPosition(0, x, 150, 1) && checkPosition(290, y, 400, 1) ) 	|| 
					( checkPosition(0, x, 250, 1) && checkPosition(390, y, 500, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(0, y, 500, 1) ) 		) check = false;
			break;
		}
		case 20:{
			if(  	( checkPosition(90, x, 500, 1) && checkPosition(0, y, 50, 1) ) 		||
					( checkPosition(40, x, 250, 1) && checkPosition(90, y, 250, 1) ) 	|| 
					( checkPosition(0, x, 50, 1) && checkPosition(390, y, 500, 1) ) 	|| 
					( checkPosition(190, x, 500, 1) && checkPosition(440, y, 500, 1) ) 		) check = false;
			break;
		}
		case 21:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 300, 1) ) 		||
					( checkPosition(0, x, 350, 1) && checkPosition(390, y, 500, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(290, y, 500, 1) ) 		) check = false;
			break;
		}
		case 22:{
			if(  	( checkPosition(190, x, 500, 1) && checkPosition(190, y, 500, 1) ) 		) check = false;
			break;
		}
		case 23:{
			if(  	( checkPosition(340, x, 500, 1) && checkPosition(90, y, 400, 1) ) 	||
					( checkPosition(0, x, 150, 1) && checkPosition(190, y, 400, 1) ) 	|| 
					( checkPosition(0, x, 150, 1) && checkPosition(440, y, 500, 1) ) 	|| 
					( checkPosition(340, x, 500, 1) && checkPosition(440, y, 500, 1) ) 		) check = false;
			break;
		}
		case 24:{
			if(  	( checkPosition(0, x, 150, 1) && checkPosition(0, y, 450, 1) ) 		||
					( checkPosition(340, x, 450, 1) && checkPosition(0, y, 150, 1) ) 	|| 
					( checkPosition(340, x, 400, 1) && checkPosition(140, y, 200, 1) ) 	|| 
					( checkPosition(440, x, 500, 1) && checkPosition(190, y, 250, 1) )	||
					( checkPosition(340, x, 500, 1) && checkPosition(240, y, 300, 1) ) 	||
					( checkPosition(290, x, 500, 1) && checkPosition(290, y, 500, 1) )		) check = false;
			break;
		}
		case 25:{
			if(  	( checkPosition(0, x, 300, 1) && checkPosition(140, y, 250, 1))   	||
				    ( checkPosition(340, x, 500, 1) && checkPosition(140, y, 250, 1)) 	|| 
				    ( checkPosition(0, x, 250, 1) && checkPosition(240, y, 400, 1)) 	||
				    ( checkPosition(390, x, 500, 1) && checkPosition(240, y, 400, 1))	||
				    ( checkPosition(440, x, 500, 1) && checkPosition(390, y, 500, 1) )		) check = false;
			break;
		}
		case 26:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 200, 1))   	||
					( checkPosition(0, x, 100, 1) && checkPosition(190, y, 400, 1)) 	|| 
				    ( checkPosition(290, x, 500, 1) && checkPosition(190, y, 350, 1)) 	||
				    ( checkPosition(290, x, 350, 1) && checkPosition(340, y, 400, 1))	||
				    ( checkPosition(390, x, 500, 1) && checkPosition(340, y, 400, 1) )	||	
				    ( checkPosition(0, x, 500, 1) && checkPosition(440, y, 500, 1) )		) check = false;
			break;
		}
		case 27:{
			if(  	( checkPosition(0, x, 150, 1) && checkPosition(0, y, 100, 1))   	||
					( checkPosition(340, x, 500, 1) && checkPosition(0, y, 100, 1)) 	|| 
				    ( checkPosition(0, x, 500, 1) && checkPosition(90, y, 300, 1)) 		||
				    ( checkPosition(0, x, 100, 1) && checkPosition(290, y, 350, 1))		||
				    ( checkPosition(340, x, 500, 1) && checkPosition(290, y, 350, 1) )	||	
				    ( checkPosition(0, x, 50, 1) && checkPosition(340, y, 400, 1) )		||
				    ( checkPosition(0, x, 200, 1) && checkPosition(440, y, 500, 1) )	||
				    ( checkPosition(290, x, 500, 1) && checkPosition(440, y, 500, 1) )		) check = false;
			break;
		}
		case 28:{
			if(  	( checkPosition(0, x, 150, 1) && checkPosition(190, y, 500, 1))   	||
				    ( checkPosition(440, x, 500, 1) && checkPosition(40, y, 500, 1)) 	|| 
				    ( checkPosition(390, x, 450, 1) && checkPosition(90, y, 150, 1)) 	||
				    ( checkPosition(340, x, 400, 1) && checkPosition(140, y, 350, 1))	||
				    ( checkPosition(340, x, 450, 1) && checkPosition(440, y, 500, 1) )		) check = false;
			break;
		}
		case 29:{
			if(  	( checkPosition(0, x, 200, 1) && checkPosition(0, y, 300, 1))   	||
				    ( checkPosition(390, x, 500, 1) && checkPosition(0, y, 500, 1)) 	|| 
				    ( checkPosition(0, x, 200, 1) && checkPosition(390, y, 500, 1)) 		) check = false;
			break;
		}	
		case 30:{
			if(  ( checkPosition(0, x, 50, 1) && checkPosition(0, y, 100, 1))   		||
			     ( checkPosition(0, x, 50, 1) && checkPosition(190, y, 450, 1)) 		|| 
			     ( checkPosition(0, x, 150, 1) && checkPosition(440, y, 500, 1)) 		||
			     ( checkPosition(390, x, 500, 2) )											) check = false;
			break;
		}
		case 31:{
			if(  	( checkPosition(0, x, 50, 1) && checkPosition(0, y, 100, 1))   		||
				    ( checkPosition(0, x, 50, 1) && checkPosition(240, y, 500, 1)) 		|| 
				    ( checkPosition(190, x, 500, 1) && checkPosition(0, y, 150, 1)) 	||
				    ( checkPosition(290, x, 500, 1) && checkPosition(140, y, 200, 1))	||
				    ( checkPosition(390, x, 500, 1) && checkPosition(190, y, 250, 1) )	||
				    ( checkPosition(190, x, 200, 1) && checkPosition(240, y, 290, 1))   ||
				    ( checkPosition(190, x, 350, 1) && checkPosition(290, y, 350, 1)) 	|| 
				    ( checkPosition(190, x, 500, 1) && checkPosition(340, y, 400, 1)) 	||
				    ( checkPosition(190, x, 400, 1) && checkPosition(390, y, 500, 1))		) check = false;
			break;
		}
		case 32:{
			if(  	( checkPosition(0, x, 300, 1) && checkPosition(0, y, 100, 1))   	||
				    ( checkPosition(0, x, 200, 1) && checkPosition(90, y, 150, 1)) 		|| 
				    ( checkPosition(0, x, 100, 1) && checkPosition(140, y, 200, 1)) 	||
				    ( checkPosition(390, x, 500, 1) && checkPosition(0, y, 500, 1))		||
				    ( checkPosition(240, x, 300, 1) && checkPosition(190, y, 300, 1) )	||
				    ( checkPosition(0, x, 50, 1) && checkPosition(290, y, 350, 1))  	||
				    ( checkPosition(90, x, 200, 1) && checkPosition(340, y, 400, 1)) 	|| 
				    ( checkPosition(0, x, 300, 1) && checkPosition(390, y, 500, 1)) 		) check = false;
			break;
		}
		case 33:{
			if(  	( checkPosition(0, x, 150, 1) && checkPosition(0, y, 200, 1))   	||
				    ( checkPosition(0, x, 100, 1) && checkPosition(190, y, 300, 1)) 	|| 
				    ( checkPosition(240, x, 300, 1) && checkPosition(0, y, 200, 1)) 	||
				    ( checkPosition(290, x, 350, 1) && checkPosition(0, y, 150, 1))		||
				    ( checkPosition(340, x, 400, 1) && checkPosition(0, y, 100, 1) )	||
				    ( checkPosition(440, x, 500, 1) && checkPosition(140, y, 200, 1))   ||
				    ( checkPosition(440, x, 500, 1) && checkPosition(240, y, 300, 1)) 	|| 
				    ( checkPosition(0, x, 100, 1) && checkPosition(390, y, 450, 1)) 	||
				    ( checkPosition(390, x, 500, 1) && checkPosition(390, y, 450, 1))	||
				    ( checkPosition(0, x, 200, 1) && checkPosition(440, y, 500, 1))   	||
				    ( checkPosition(290, x, 500, 1) && checkPosition(440, y, 500, 1)) 		) check = false;
			break;
		}
		case 34:{
			if(  	( checkPosition(0, x, 350, 1) && checkPosition(0, y, 200, 1))   	||
				    ( checkPosition(0, x, 50, 1) && checkPosition(290, y, 500, 1)) 		|| 
				    ( checkPosition(90, x, 350, 1) && checkPosition(290, y, 500, 1)) 		) check = false;
			break;
		}
		case 35:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 150, 1))   	||
				    ( checkPosition(0, x, 50, 1) && checkPosition(340, y, 450, 1)) 		|| 
				    ( checkPosition(0, x, 500, 1) && checkPosition(440, y, 500, 1)) 	||
				    ( checkPosition(290, x, 500, 1) && checkPosition(340, y, 450, 1))	   	) check = false;
			break;
		}
		case 36:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 150, 1))   	||
				    ( checkPosition(40, x, 500, 1) && checkPosition(140, y, 200, 1)) 	|| 
				    ( checkPosition(90, x, 500, 1) && checkPosition(190, y, 250, 1)) 	||
				    ( checkPosition(140, x, 500, 1) && checkPosition(240, y, 300, 1))	||
				    ( checkPosition(190, x, 500, 1) && checkPosition(290, y, 350, 1) )	||
				    ( checkPosition(0, x, 50, 1) && checkPosition(240, y, 300, 1))   	||
				    ( checkPosition(0, x, 100, 1) && checkPosition(290, y, 350, 1)) 	|| 
				    ( checkPosition(0, x, 150, 1) && checkPosition(340, y, 400, 1)) 	||
				    ( checkPosition(0, x, 200, 1) && checkPosition(390, y, 500, 1))		||
				    ( checkPosition(240, x, 500, 1) && checkPosition(440, y, 500, 1))   	) check = false;
			break;
		}
		case 37:{
			if(  	( checkPosition(0, x, 200, 1) && checkPosition(0, y, 50, 1))   		||
				    ( checkPosition(240, x, 500, 1) && checkPosition(0, y, 50, 1)) 		|| 
				    ( checkPosition(290, x, 500, 1) && checkPosition(90, y, 250, 1)) 	||
				    ( checkPosition(0, x, 500, 1) && checkPosition(140, y, 300, 1))		||
				    ( checkPosition(40, x, 200, 1) && checkPosition(290, y, 400, 1) )	||
				    ( checkPosition(290, x, 450, 1) && checkPosition(290, y, 400, 1))   ||
				    ( checkPosition(40, x, 200, 1) && checkPosition(440, y, 500, 1)) 	||
				    ( checkPosition(290, x, 500, 1) && checkPosition(440, y, 500, 1))		 ) check = false;
			break;
		}
		case 38:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 50, 1))   		||
				    ( checkPosition(0, x, 500, 1) && checkPosition(90, y, 300, 1)) 		|| 
				    ( checkPosition(40, x, 200, 1) && checkPosition(290, y, 400, 1)) 	||
				    ( checkPosition(290, x, 450, 1) && checkPosition(290, y, 400, 1))	||
				    ( checkPosition(40, x, 200, 1) && checkPosition(440, y, 500, 1) )	||
				    ( checkPosition(290, x, 450, 1) && checkPosition(440, y, 500, 1))   	) check = false;
			break;
		}
		case 39:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(0, y, 400, 1))   		) check = false;
			
			break;
		}
		case 40:{
			if(  	( checkPosition(0, x, 500, 1) && checkPosition(290, y, 500, 1))   	||
				    ( checkPosition(290, x, 500, 1) && checkPosition(90, y, 300, 1)) 	|| 
				    ( checkPosition(0, x, 200, 1) && checkPosition(90, y, 200, 1)) 	      	) check = false;
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + mapNum);
		}
		return check;
	}
	// 해당 맵에 상호작용 건물의 위치를 알려줘서 그 위치에 있는 건물이 방문목록에 있는지 확인. | 상호작용한 건물이 방문목록에 true : 있다 ,false : 없다 | 있다면 어떤 건물인지 keyMap 저장
	boolean missionSpace(int mapNum, int x, int y) {
		boolean check = false;
		switch (mapNum) {
			case 1: {
				if( escape.missionMap[5] != -1 ) {
					if( checkPosition(0, x, 80, 1) && y == 330 ) {GamePanel.keyMap = 5; check = true;}
				}
				break;
			}
			case 3: {
				if( escape.missionMap[6] != -1 ) {
					if( x == 400 && checkPosition(350, y, 500, 1) ) {GamePanel.keyMap = 6; check = true;}
				}
				if( escape.missionMap[7] != -1 ) {
					if( checkPosition(400, x, 500, 1) && y == 100 ) {GamePanel.keyMap = 7; check = true;}
				}
				break;
			}
			case 6: {
				if( escape.missionMap[11] != -1 ) {
					if( checkPosition(50, x, 100, 1) && y == 400 ) {GamePanel.keyMap = 11; check = true;}
				}
				break;
			}
			case 11: {
				if( escape.missionMap[16] != -1 ) {
					if( x == 200 && checkPosition(200, y, 250, 1) ) {GamePanel.keyMap = 16; check = true;}
				}
				break;
			}
			case 12: {
				if( escape.missionMap[17] != -1 ) {
					if( checkPosition(250, x, 300, 1) && y == 80 ) {GamePanel.keyMap = 17; check = true;}
				}
				if( escape.missionMap[18] != -1 ) {
					if( checkPosition(250, x, 300, 1) && y == 330 ) {GamePanel.keyMap = 18; check = true;}
				}
				break;
			}
			case 13: {
				if( escape.missionMap[19] != -1 ) {
					if(checkPosition(50, x, 150, 1) && y == 280 ) {GamePanel.keyMap = 19; check = true;}
				}
				break;
			}
			case 14: {
				if( escape.missionMap[20] != -1 ) {
					if( checkPosition(200, x, 250, 1) && y == 180 ) {GamePanel.keyMap = 20; check = true;}
				}
				if( escape.missionMap[21] != -1 ) {
					if( checkPosition(450, x, 500, 1) && y == 380 ) {GamePanel.keyMap = 21; check = true;}
				}
				if( escape.missionMap[26] != -1 ) {
					if( x == 100 && checkPosition(50, y, 150, 1)) {GamePanel.keyMap = 26; check = true;}
				}
				break;
			}
			case 15: {
				if( escape.missionMap[9] != -1 ) {
					if( x == 150 && checkPosition(400, y, 450, 1) ) {GamePanel.keyMap = 9; check = true;}
				}
				if( escape.missionMap[10] != -1 ) {
					if( x == 100 && checkPosition(0, y, 50, 1) || checkPosition(100, x, 150, 1) && y == 0) {GamePanel.keyMap = 10; check = true;}
				}
				if( escape.missionMap[24] != -1 ) {
					if( checkPosition(250, x, 350, 1) && y == 430) {GamePanel.keyMap = 24; check = true;}
				}
				break;
			}
			case 18: {
				if( escape.missionMap[8] != -1 ) {
					if( x == 200 && checkPosition(200, y, 300, 1) ) {GamePanel.keyMap = 8; check = true;}
				}
				break;
			}
			case 19: {
				if( escape.missionMap[12] != -1 ) {
					if( x == 400 && checkPosition(0, y, 50, 1) ) {GamePanel.keyMap = 12; check = true;}
				}
				if( escape.missionMap[13] != -1 ) {
					if( x == 250 && checkPosition(400, y, 500, 1) ) {GamePanel.keyMap = 13; check = true;}
				}
				break;
			}
			case 21: {
				if( escape.missionMap[15] != -1 ) {
					if( checkPosition(200, x, 400, 1) && y == 300 ) {GamePanel.keyMap = 15; check = true;}
				}
				break;
			}
			case 23: {
				if( escape.missionMap[1] != -1 ) {
					if( x == 330 && checkPosition(250, y, 300, 1) ){GamePanel.keyMap = 1; check = true;}
				}
				break;
			}
			case 25: {
				if( escape.missionMap[2] != -1 ) {
					if( x == 250 && checkPosition(250, y, 400, 1) ) {GamePanel.keyMap = 2; check = true;}
				}
				break;
			}
			case 27: {
					if( escape.missionMap[0] != -1 ) {
						if( checkPosition(200, x, 300, 1) && y == 300 ) {GamePanel.keyMap = 0; check = true;}
					}
					break;
			}
			case 28: {
				if( escape.missionMap[23] != -1 ) {
					if( x == 430 && checkPosition(200, y, 300, 1)) {GamePanel.keyMap = 23; check = true;}
				}
				break;
			}
			case 31: {
				if( escape.missionMap[22] != -1 ) {
					if( x == 400 && checkPosition(400, y, 500, 1) ) {GamePanel.keyMap = 22; check = true;}
				}
				if( escape.missionMap[25] != -1 ) {
					if( x == 180 && checkPosition(50, y, 100, 1) ) {GamePanel.keyMap = 25; check = true;}
				}
				break;
			}
			case 37: {
				if( escape.missionMap[3] != -1 ) {
					if( checkPosition(200, x, 300, 1) && y == 300 ) {GamePanel.keyMap = 3; check = true;}
				}
				break;
			}
			case 38: {
				if( escape.missionMap[4] != -1 ) {
					if( checkPosition(200, x, 300, 1) && y == 300 ) {GamePanel.keyMap = 4; check = true;}
				}
				break;
			}
			case 40: {
				if( escape.missionMap[14] != -1 ) {
					if( checkPosition(200, x, 300, 1) && y == 280 ) {GamePanel.keyMap = 14; check = true;}
				}
				break;
			}
		}
		
		return check;
	}
	// 범위를 알려주는 함수
	boolean checkPosition(int minPos, int pos, int maxPos, int state) {
		boolean check = false;
		switch (state){
		case 1: {
			if( minPos <= pos && pos < maxPos ) check = true;
			break;
		}
		case 2 : {
			if( minPos<= pos) check = true;
			break;
		}
		case 3: {
			if( pos < maxPos) check = true;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
		return check;
	}
	
	
}
