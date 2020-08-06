package com.yc.favorites;

import org.apache.ibatis.session.SqlSession;

import com.yc.favorites.bean.Favorite;
import com.yc.favorites.bean.Tag;
import com.yc.favorites.bean.TagFavorite;
import com.yc.favorites.dao.FavoriteMapper;
import com.yc.favorites.dao.TagFavoriteMapper;
import com.yc.favorites.dao.TagMapper;
import com.yc.favorites.util.MyBatisHelper;

public class FavoriteBiz {
	
	public void addFavorite(Favorite f) {
		
		new MyBatisHelper();
		SqlSession session = MyBatisHelper.openSession();
		FavoriteMapper fmapper = session.getMapper(FavoriteMapper.class);
		TagMapper tmapper = session.getMapper(TagMapper.class);
		TagFavoriteMapper tfmapper = session.getMapper(TagFavoriteMapper.class);
		
		try {
			//将链接添加到favorite insert方法会带回fid的值
			fmapper.insert(f);
			//拆分ftags
			String[] tags = f.getFtags().split("[,;，；]");
			for(String tag: tags) {
				Tag tagObj = new Tag();
				if(tmapper.updateCount(tag) == 0) {
					//如果返回的结果为0，那么说明没有记录，就新增
					tagObj.setTname(tag);
					tmapper.insert(tagObj);
				} else {
					tagObj = tmapper.selectByName(tag);
				}
				TagFavorite tf = new TagFavorite();
				tf.setTid(tagObj.getTid());
				tf.setFid(f.getFid());
				tfmapper.insert(tf);
				session.commit();
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}
