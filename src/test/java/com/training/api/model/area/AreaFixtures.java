package com.training.api.model.area;

import com.training.api.model.city.City;
import com.training.api.model.oldpost.OldPost;
import com.training.api.model.post.Post;
import com.training.api.model.prefecture.Prefecture;

/**
 * Create area test data
 */
public class AreaFixtures {
    public static Area createArea() {
        Prefecture prefecture = new Prefecture();
        prefecture.setPrefectureId(01);
        prefecture.setPrefectureName("秋田県");
        prefecture.setPrefectureCode("05");
        prefecture.setPrefectureKana("ｱｷﾀｹﾝ");

        City city = new City();
        city.setCityId(01);
        city.setCityName("湯沢市");
        city.setCityKana("ﾕｻﾞﾜｼ");
        city.setCityCode("05207");
        city.setPrefecture(prefecture);

        Post post = new Post();
        post.setPostId(01);
        post.setPostCode("0120833");
        post.setUpdateShow(0);
        post.setMultiArea(0);
        post.setChangeReason(0);

        OldPost oldPost = new OldPost();
        oldPost.setOldPostId(01);
        oldPost.setOldPostCode("012");

        Area area = new Area();
        area.setAreaId(01);
        area.setAreaName("カツクイ沢山");
        area.setAreaKana("ｶｯｸｲｻﾜﾔﾏ");
        area.setCity(city);
        area.setChomeArea(0);
        area.setKoazaArea(0);
        area.setMultiPostArea(0);
        area.setPost(post);
        area.setOldPost(oldPost);

        return area;
    }
}
