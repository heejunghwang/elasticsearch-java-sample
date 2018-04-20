package com.example.service;

import com.example.model.Review;
import com.vividsolutions.jts.util.Assert;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InfoServiceTest.class)
@ComponentScan(basePackages = "com.example")
public class IndexServiceTest {

    @Autowired
    IndexService indexService;

    @Test
    public void deleteIndexTest() throws IOException{
        DeleteIndexResponse response = indexService.deleteIndex("shopping");
        Assert.equals(response.isAcknowledged(), true);
    }

    @Test
    public void createIndexTest() throws IOException{
        String index = "shopping";
        String type = "review";
        CreateIndexResponse response = indexService.createIndex(index, type);
        Assert.equals(response.isAcknowledged(), true);
    }

    @Test
    public void bulkIndexTest() throws Exception{
        String index = "shopping";
        String type = "review";
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review("Dresses", "Flattering","I love this dress. i usually get an xs but it runs a little snug in bust so i ordered up a size. very flattering and feminine with the usual retailer flair for style."));
        reviewList.add(new Review("Tops", "Looks great with white pants", "Took a chance on this blouse and so glad i did. i wasn't crazy about how the blouse is photographed on the model. i paired it whit white pants and it worked perfectly. crisp and clean is how i would describe it. launders well. fits great. drape is perfect. wear tucked in or out - can't go wrong."));
        reviewList.add(new Review("Jackets", "Super cute and cozy", "A flattering, super cozy coat.  will work well for cold, dry days and will look good with jeans or a dressier outfit.  i am 5' 5'', about 135 and the small fits great."));
        BulkResponse response = indexService.bulkIndex(index, type, reviewList);
        Assert.equals(response.status(), RestStatus.OK);
    }
}
