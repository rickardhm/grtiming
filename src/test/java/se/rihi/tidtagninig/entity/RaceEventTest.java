package se.rihi.tidtagninig.entity;

import org.junit.Assert;
import org.junit.Test;
import se.rihi.tidtagninig.support.AssertAnnotations;
import se.rihi.tidtagninig.support.ReflectTool;

import javax.persistence.*;

public class RaceEventTest implements TestEntityInterface {
    @Override
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(RaceEvent.class, NamedQueries.class, Entity.class, Table.class);
    }

    @Override
    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(RaceEvent.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Override
    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(RaceEvent.class, Table.class);
        Assert.assertEquals("race_event", t.name());
    }

    @Override
    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(RaceEvent.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(RaceEvent.class, "description", Column.class);
        AssertAnnotations.assertField(RaceEvent.class, "date", Column.class);
        AssertAnnotations.assertField(RaceEvent.class, "eventLocation", Column.class);

    }

    @Override
    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(RaceEvent.class, "getId");
        AssertAnnotations.assertMethod(RaceEvent.class, "getDescription");
        AssertAnnotations.assertMethod(RaceEvent.class, "getDate");
        AssertAnnotations.assertMethod(RaceEvent.class, "getEventLocation");
    }
}
