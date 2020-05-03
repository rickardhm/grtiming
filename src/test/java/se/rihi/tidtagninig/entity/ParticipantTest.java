package se.rihi.tidtagninig.entity;

import org.junit.Assert;
import org.junit.Test;
import se.rihi.tidtagninig.support.AssertAnnotations;
import se.rihi.tidtagninig.support.ReflectTool;

import javax.persistence.*;

public class ParticipantTest implements TestEntityInterface {

    @Override
    @Test
    public void typeAnnotations() {
        // assert
        AssertAnnotations.assertType(Participant.class, NamedQueries.class, Entity.class, Table.class);
    }

    @Override
    @Test

    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Participant.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Override
    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Participant.class, Table.class);
        Assert.assertEquals("participant", t.name());
    }

    @Override
    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Participant.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Participant.class, "name", Column.class);
        AssertAnnotations.assertField(Participant.class, "email", Column.class);
        AssertAnnotations.assertField(Participant.class, "phone", Column.class);
        AssertAnnotations.assertField(Participant.class, "club", Column.class);
        AssertAnnotations.assertField(Participant.class, "age", Column.class);
        AssertAnnotations.assertField(Participant.class, "sex", Column.class);
    }

    @Override
    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Participant.class, "getName");
        AssertAnnotations.assertMethod(Participant.class, "getEmail");
        AssertAnnotations.assertMethod(Participant.class, "getPhone");
        AssertAnnotations.assertMethod(Participant.class, "getClub");
        AssertAnnotations.assertMethod(Participant.class, "getAge");
        AssertAnnotations.assertMethod(Participant.class, "getSex");
    }

}