package se.rihi.tidtagninig.system.entity;

import org.junit.Assert;
import org.junit.Test;
import se.rihi.tidtagninig.support.AssertAnnotations;
import se.rihi.tidtagninig.support.ReflectTool;

import javax.persistence.*;

public class RaceTest implements TestEntityInterface {
    @Override
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(Race.class, NamedQueries.class, Entity.class, Table.class);
    }

    @Override
    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Race.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Override
    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Race.class, Table.class);
        Assert.assertEquals("race", t.name());
    }

    @Override
    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Race.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Race.class, "participants", OneToMany.class, JoinColumn.class);
        AssertAnnotations.assertField(Race.class, "name", Column.class);
        AssertAnnotations.assertField(Race.class, "description", Column.class);
        AssertAnnotations.assertField(Race.class, "raceDate", Column.class);
        AssertAnnotations.assertField(Race.class, "fee", Column.class);
        AssertAnnotations.assertField(Race.class, "distance", Column.class);
    }

    @Override
    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Race.class, "getId");
        AssertAnnotations.assertMethod(Race.class, "getParticipants");
        AssertAnnotations.assertMethod(Race.class, "getName");
        AssertAnnotations.assertMethod(Race.class, "getDescription");
        AssertAnnotations.assertMethod(Race.class, "getFee");
        AssertAnnotations.assertMethod(Race.class, "getDistance");
    }
}
