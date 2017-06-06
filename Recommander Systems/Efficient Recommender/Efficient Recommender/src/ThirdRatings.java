import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }

	public ThirdRatings(String ratingsfile) {
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(ratingsfile);
	}
	

	public int  getRaterSize(){
		return myRaters.size();
	}
    
	//id is the movie's id 
	private double getAverageByID(String id, int minimalRaters){
		//use Rater's method getRating
		double count = 0;
		//do not use int here, use double instead
		double tRate = 0;
		for(EfficientRater r : myRaters){
			if(r.hasRating(id)){
				count += 1;
				tRate += r.getRating(id);
			}
		}
		
		if(count >= minimalRaters){
			return (double)(tRate/count);
		}
		
		return (double)0.0;
	}	
	
	//create new Rating for each movie, but use average rat for the rating
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> result = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(String id : movies){
			if(getAverageByID(id, minimalRaters) > 0){
				Rating rating = new Rating(id,getAverageByID(id, minimalRaters));
				result.add(rating);
			}
		}
		
		return result;
	}
	
	public ArrayList<Rating>  getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
		ArrayList<Rating> result = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for(String m : movies){
			if(getAverageByID(m,minimalRaters) > 0){
				Rating rating = new Rating(m,getAverageByID(m,minimalRaters));
				result.add(rating);
			}
		}
		return result;	
	}
	

	
}
