package se.rihi.tidtagninig.entity;

import org.junit.Assert;
import org.junit.Test;
import se.rihi.tidtagninig.support.AssertAnnotations;
import se.rihi.tidtagninig.support.ReflectTool;

import javax.persistence.*;

public class FinishListTest implements TestEntityInterface {

    @Override
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(FinishList.class, NamedQueries.class, Entity.class, Table.class);
    }

    @Override
    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(FinishList.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Override
    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(FinishList.class, Table.class);
        Assert.assertEquals("finishList", t.name());
    }

    @Override
    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(FinishList.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(FinishList.class, "finishTime", Column.class);
        AssertAnnotations.assertField(FinishList.class, "nr", Column.class);
        AssertAnnotations.assertField(FinishList.class, "position", Column.class);
    }

    @Override
    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(FinishList.class, "getId");
        AssertAnnotations.assertMethod(FinishList.class, "getFinishTime");
        AssertAnnotations.assertMethod(FinishList.class, "getNr");
        AssertAnnotations.assertMethod(FinishList.class, "getPosition");
    }
}
