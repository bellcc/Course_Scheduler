public enum enumCoursePreReqs {

	CSE102 (false, new String[] {}),
	CSE141 (false, new String[] {}),
	CSE148 (false, new String[] {}),
	CSE151 (false, new String[] {}),
	CSE153 (false, new String[] {}),
	CSE163 (false, new String[] {}),
	CSE174 (false, new String[] {}),
	CSE177 (false, new String[] {}),
	CSE201 (true, new String[] {"CSE271"} ),
	CSE211 (true, new String[] {"CSE201", "CSE274", "MTH231"} ),
	CSE212 (true, new String[] {"CSE271"} ),
	CSE241 (true, new String[] {"MTH151"} ),
	CSE251 (false, new String[] {}),
	CSE252 (true, new String[] {}), // CSE153 OR CSE163 OR CSE174
	CSE253 (false, new String[] {}),
	CSE262 (true, new String[] {"ENG111"} ), // & 20 credits earned
	CSE270 (false, new String[] {}),
	CSE271 (true, new String[] {"CSE174"} ),
	CSE273 (true, new String[] {"MTH251"} ),
	CSE274 (true, new String[] {"CSE271", "MTH231" } ),
	CSE277 (false, new String[] {}),
	CSE278 (true, new String[] {"CSE271"} ),
	CSE283 (true, new String[] {"CSE271"} ),
	CSE310 (false, new String[] {}),
	CSE311 (true, new String[] {"CSE201"} ),
	CSE320 (false, new String[] {}),
	CSE321 (true, new String[] {"CSE201"} ),
	CSE322 (true, new String[] {"CSE201"} ),
	CSE340 (false, new String[] {}),
	CSE372 (true, new String[] {"STA368"} ), // or concurrent registration in STA401
	CSE377 (false, new String[] {}),
	CSE381 (true, new String[] {"CSE274", "CSE278"} ),
	CSE383 (true, new String[] {"CSE274", "CSE283"} ),
	CSE385 (true, new String[] {"CSE274"} ), // or concurrent registration
	CSE386 (true, new String[] {"CSE274", "MTH231"} ),
	CSE441 (true, new String[] {"CSE102", "STA368"} ), // && MTH245 OR MTH347
	CSE443 (true, new String[] {"CSE278"} ),
	CSE448 (true, new String[] {"CSE201", "CSE274"} ), // & senior standing
	CSE449 (true, new String[] {"CSE448"} ), // & senior standing
	CSE451 (true, new String[] {"CSE274", "CSE283"} ), 
	CSE464 (true, new String[] {"CSE274", "MTH231"} ),
	CSE465 (true, new String[] {"CSE274"} ),
	CSE466 (true, new String[] {}), // BOT116 ORBOT342
	CSE467 (true, new String[] {"CSE283", "CSE383"} ),
	CSE470 (false, new String[] {}),
	CSE471 (true, new String[] {"CSE174"} ), // & STA368 OR STA401
	CSE473 (true, new String[] {"CSE274", "MTH231"} ),
	CSE474 (true, new String[] {"CSE274"} ),
	CSE477 (false, new String[] {}),
	CSE480 (false, new String[] {}),
	CSE481 (true, new String[] {}), // senior standing
	CSE483 (true, new String[] {"STA401", "STA462"} ),
	CSE485 (true, new String[] {"CSE385", "MTH231"} ),
	CSE486 (true, new String[] {"CSE274", "MTH231"} ),
	CSE487 (true, new String[] {"CSE386"} ),
	CSE491 (false, new String[] {});
	
	private boolean hasPrereqs;
	private String[] coursePrereqs;
	
	private enumCoursePreReqs(boolean hasPrereqs, String[] coursePrereqs) {
		this.hasPrereqs = hasPrereqs;
		this.coursePrereqs = coursePrereqs;
	}
	
	public boolean hasPrerequisites()	{	return hasPrereqs;		}
	public String[] getCoursePrereqs()	{	return coursePrereqs;	}
	
}
