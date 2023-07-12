package model.rating;

public class Rating {
	private int ratingNo;
	private int postNo;
	private String rater;
	private String rated;
	private String content;
	private int score;
	private int curse;
	private int run;
	private int late;
	private int disturb;
	private int hack;
	private boolean finish;
	
	
	public Rating(int postNo, String rater, String rated, String content, int score, int curse, int run, int late,
			int disturb, int hack, boolean finish) {
		super();
		this.postNo = postNo;
		this.rater = rater;
		this.rated = rated;
		this.content = content;
		this.score = score;
		this.curse = curse;
		this.run = run;
		this.late = late;
		this.disturb = disturb;
		this.hack = hack;
		this.finish = finish;
	}
	public int getRatingNo() {
		return ratingNo;
	}
	public void setRatingNo(int ratingNo) {
		this.ratingNo = ratingNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCurse() {
		return curse;
	}
	public void setCurse(int curse) {
		this.curse = curse;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getDisturb() {
		return disturb;
	}
	public void setDisturb(int disturb) {
		this.disturb = disturb;
	}
	public int getHack() {
		return hack;
	}
	public void setHack(int hack) {
		this.hack = hack;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	
	
	
	
}
