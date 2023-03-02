package com.example.demo.dao.Impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.JOINEntity;

import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;
import com.example.demo.Payloads.CommentDto;
import com.example.demo.Payloads.PostDto;
import com.example.demo.dao.PostDao;

@Repository
public class PostDaoImpls implements PostDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<JOINEntity> createPost(PostDto postDto, int category_id,int user_id) {
		// TODO Auto-generated method stub
		
		Post post=this.modelMapper.map(postDto, Post.class);
		
		String query="CREATE TABLE IF NOT EXISTS POST(post_id int primary key auto_increment,title varchar(100),content varchar(200),imageName varchar(50),addedDate Date,category_id int ,foreign key(category_id) REFERENCES category(category_id),user_id int ,foreign key(user_id) REFERENCES users(user_id))";
		this.jdbcTemplate.update(query);
	
		 String query1="CREATE TABLE IF NOT EXISTS comment(comment_id int primary key auto_increment,contentOfComment varchar(200),post_id_ofComment int ,foreign key(post_id_ofComment) REFERENCES post(post_id) ON DELETE CASCADE,user_id_ofComment int ,foreign key(user_id_ofComment) REFERENCES users(user_id) ON DELETE CASCADE)";
			
			this.jdbcTemplate.update(query1);
			
		
		this.jdbcTemplate.update("INSERT INTO POST(title,content,imageName,addedDate,category_id,user_id) VALUES(?,?,?,?,?,?)", new Object[] {post.getTitle(),post.getContent(),post.getImageName(),post.getAddedDate(),category_id,user_id});
		
		List<CommentDto> checkList=this.jdbcTemplate.query("select * from comment", new BeanPropertyRowMapper<CommentDto>(CommentDto.class));
		
		if(checkList.size()==0) {
			
			 List<JOINEntity> joinList1=this.jdbcTemplate.query(" select * from post JOIN USERS ON post.user_id=users.user_id JOIN category ON post.category_id=category.category_id ", new BeanPropertyRowMapper<JOINEntity>(JOINEntity.class));
				
			return joinList1;
		}else {
		
	    List<JOINEntity> joinList=this.jdbcTemplate.query(" select * from post JOIN USERS ON post.user_id=users.user_id JOIN category ON post.category_id=category.category_id JOIN comment ON post.post_id=comment.post_id_ofComment", new BeanPropertyRowMapper<JOINEntity>(JOINEntity.class));
		
		return joinList;
		}
		}

	@Override
	public JOINEntity updatePost(PostDto postDto,int post_id) {
		// TODO Auto-generated method stub
		
		Post post=this.modelMapper.map(postDto, Post.class);
		
		this.jdbcTemplate.update("update post set title=?,content=? where post_id=?",new Object[] {post.getTitle(),post.getContent(),post_id});
		
		JOINEntity updated=this.jdbcTemplate.queryForObject(" select * from post JOIN USERS ON post.user_id=users.user_id JOIN category ON post.category_id=category.category_id JOIN comment ON post.post_id=comment.post_id_ofComment where post_id=?;", new BeanPropertyRowMapper<JOINEntity>(JOINEntity.class),post_id);
		
		return updated;
	}

	@Override
	public JOINEntity getPostById(int post_id) {
		// TODO Auto-generated method stub
		
		JOINEntity post=this.jdbcTemplate.queryForObject(" select * from post JOIN USERS ON post.user_id=users.user_id JOIN category ON post.category_id=category.category_id JOIN comment ON post.post_id=comment.post_id_ofComment where post_id=?;", new BeanPropertyRowMapper<JOINEntity>(JOINEntity.class),post_id);
		
		return post;
	}

	@Override
	public void deletePost(int post_id) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update("delete from post where post_id=?",post_id);
		
	}

	@Override
	public List<JOINEntity> getAllPost() {
		// TODO Auto-generated method stub
		
		List<JOINEntity> joinList=this.jdbcTemplate.query(" select * from post JOIN USERS ON post.user_id=users.user_id JOIN category ON post.category_id=category.category_id JOIN comment ON post.post_id=comment.post_id_ofComment", new BeanPropertyRowMapper<JOINEntity>(JOINEntity.class));
		
		return joinList;
	}

	@Override
	public List<Post> getPostByCategory(int category_id) {
		// TODO Auto-generated method stub
		
		List<Post> post=this.jdbcTemplate.query(" select post.post_id,title,content,imageName,addedDate from post  where category_id=?", new BeanPropertyRowMapper<Post>(Post.class), category_id);
		
		
		return post;
	}

	@Override
	public List<Post> getPostByUser(int user_id) {
		// TODO Auto-generated method stub
		
		List<Post> post=this.jdbcTemplate.query("select post.post_id,title,content,imageName,addedDate from post  where user_id=?", new BeanPropertyRowMapper<Post>(Post.class), user_id);
		
		
		return post;
	}

	@Override
	public int getUserByPostId(int postId) {
	
		JOINEntity post=this.jdbcTemplate.queryForObject(" select * from post JOIN USERS ON post.user_id=users.user_id JOIN category ON post.category_id=category.category_id JOIN comment ON post.post_id=comment.post_id_ofComment where post_id=?;", new BeanPropertyRowMapper<JOINEntity>(JOINEntity.class),postId);
	
		int user_id=post.getUser_id();
	
		return user_id;
	}

}
