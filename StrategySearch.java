package cu.cs.cpsc215.project2;

public interface StrategySearch {
	
	/**
	 * 
	 * @param list is the database to look through
	 * @param b is a string for comparing
	 * @return a new database made of search results
	 * This starts the strategy pattern implementation 
	 * for catalogueWindow's search button
	 */
	
	public BookDatabase fSearch(BookDatabase list, String b);
	public BookDatabase nSearch(BookDatabase list, String b);

}
