package com.example.demo.dao.Impls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Comment;
import com.example.demo.Payloads.CommentDto;
import com.example.demo.dao.CommentDao;
@Repository
public class CommentDaoImpls implements CommentDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CommentDto> createComment(CommentDto commentDto, int post_id, int user_id) {
		// TODO Auto-generated method stub
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		
		  String query="CREATE TABLE IF NOT EXISTS comment(comment_id int primary key auto_increment,contentOfComment varchar(200) NOT NULL,post_id_ofComment int ,foreign key(post_id_ofComment) REFERENCES post(post_id) ON DELETE CASCADE,user_id_ofComment int ,foreign key(user_id_ofComment) REFERENCES users(user_id) ON DELETE CASCADE)";
			
			this.jdbcTemplate.update(query);
			
		
		this.jdbcTemplate.update("INSERT INTO comment(contentOfComment,post_id_ofComment,user_id_ofComment) VALUES(?,?,?)", new Object[] {commentDto.getContentOfComment(),post_id,user_id});
		
	     List<CommentDto> list=this.jdbcTemplate.query("select * from comment", new BeanPropertyRowMapper<CommentDto>(CommentDto.class));
		
		return list;
	}

	@Override
	public void deleteComment(int comment_id) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update("delete from comment where comment_id=?",comment_id);
		
	}

	@Override
	public CommentDto getCommentById(int comment_id) {
		// TODO Auto-generated method stub
		
		CommentDto get= this.jdbcTemplate.queryForObject("select * from comment where comment_id=?",new BeanPropertyRowMapper<CommentDto>(CommentDto.class),comment_id);
		
		return get;
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto,int comment_id) {
		// TODO Auto-generated method stub
		Comment comment=this.modelMapper.map(commentDto, Comment.class);

		this.jdbcTemplate.update("update comment set contentOfComment=? where comment_id=?",new Object[] {comment.getContentOfComment(),comment_id});
		
		CommentDto updated= this.jdbcTemplate.queryForObject("select * from comment where comment_id=?",new BeanPropertyRowMapper<CommentDto>(CommentDto.class),comment_id);
	
		return updated;
	}

	@Override
	public List<CommentDto> getAllComments() {
		// TODO Auto-generated method stub
		
		List<CommentDto> list=this.jdbcTemplate.query("select * from comment", new BeanPropertyRowMapper<CommentDto>(CommentDto.class));
		
		return list;
	}

	@Override
	public List<CommentDto> getCommentByUser(int user_id) {
		// TODO Auto-generated method stub
		
		List<CommentDto> list= this.jdbcTemplate.query("select * from comment where user_id_ofComment=?", new BeanPropertyRowMapper<CommentDto>(CommentDto.class), user_id);
		
		return list;
	}

	@Override
	public List<CommentDto> getCommentByPost(int post_id) {
		// TODO Auto-generated method stub
		
		List<CommentDto> list= this.jdbcTemplate.query("select * from comment where post_id_ofComment=?", new BeanPropertyRowMapper<CommentDto>(CommentDto.class), post_id);
		
		return list;
	}

	
}
