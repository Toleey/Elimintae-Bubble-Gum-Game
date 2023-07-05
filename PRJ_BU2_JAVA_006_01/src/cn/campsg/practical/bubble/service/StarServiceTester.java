package cn.campsg.practical.bubble.service;

import cn.campsg.practical.bubble.entity.Position;
import cn.campsg.practical.bubble.entity.Star;
import cn.campsg.practical.bubble.entity.Star.StarType;
import cn.campsg.practical.bubble.entity.StarList;

public class StarServiceTester implements StarService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 StarService service = new StarServiceTester();
	        StarList stars = service.createStars();
	        System.out.println(stars);

	}

	@Override
	public StarList createStars() {
		// TODO Auto-generated method stub
	    StarList stars = new StarList();
	       
        Star star = new Star();
        star.setPosition(new Position(0, 0));
        star.setType(StarType.BLUE);
        stars.add(star);
       
        star = new Star();
        star.setPosition(new Position(0, 1));
        star.setType(StarType.GREEN);
        stars.add(star);
       
        star = new Star();
        star.setPosition(new Position(1, 0));
        star.setType(StarType.PURPLE);
        stars.add(star);
       
        star = new Star();
        star.setPosition(new Position(1, 1));
        star.setType(StarType.YELLOW);
        stars.add(star);
       
        star = new Star();
        star.setPosition(new Position(0, 2));
        star.setType(StarType.RED);
        stars.add(star);
           
        return stars;
	}

	@Override
	public StarList tobeClearedStars(Star base, StarList sList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StarList getYMovedStars(StarList clearStars, StarList currentStarList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StarList getXMovedStars(StarList currentStarList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tobeEliminated(StarList currentStarList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StarList getAwardStarList(StarList currentStarList) {
		// TODO Auto-generated method stub
		return null;
	}

}
