# ShoutBox
ShoutBox is a like a social networking website where users can post comments, share photographs, videos and audios.  The main objective of this system is to create a dynamic web application where USER can :
Login and register , 
Post shouts which includes uploading text, photos, videos and audios ,
See friends shouts and own shouts  , 
Comment on his own shouts as well as his friends shouts ,
Report friends shouts , 
See pending list of requests which are not yet confirmed ,
Send friend request, accept or reject friend requests , 
Unfriend already made friend .
ADMIN functionality : 
Approve new users ,
Delete or view all approved users , 
View shouts of approved users ,
Delete reported shouts  
We used Spring Boot Framework, SCSS, Bootstrap 4, HTML 5, Angular 7 and MySQL to design ShoutBox.

INSERT THE FOLLOWING STORED PROCEDURES IN DATABASE :

1) PROC_FRIEND_REQUEST_ACCEPT_REJECTED

CREATE PROCEDURE `PROC_FRIEND_REQUEST_ACCEPT_REJECTED`(IN sentFlag varchar(20),IN pendingFlag varchar(20), IN currentUserId int, IN currentFriendId int)

	BEGIN
	
		declare rowsAffected int;
    
		select count(*) into @countVal from user_friends where (friend=currentUserId and friend_owner=currentFriendId) or (friend_owner=currentUserId or friend=currentFriendId);
    
		if(@countVal=2) then
		
			update user_friends set flag=sentFlag where friend_owner= currentFriendId and friend=currentUserId;
        
			update user_friends set flag=pendingFlag where friend= currentFriendId and friend_owner=currentUserId;
    
		else    
		
			insert into user_friends(flag,friend_owner,friend) values(pendingFlag,currentUserId,currentFriendId);
		
			insert into user_friends(flag,friend_owner,friend) values(sentFlag,currentFriendId,currentUserId);
	
		END IF;

	END

2) PROC_REJECT_APPROVED_FRIENDS

CREATE PROCEDURE `PROC_REJECT_APPROVED_FRIENDS`(IN flag varchar(20), IN currentUserId int, IN currentFriendId int)
	
BEGIN
	
		update user_friends set flag=flag where friend_owner=currentFriendId and friend=currentUserId;
    
		update user_friends set flag=flag where friend_owner=currentUserId and friend=currentFriendId;

	END