package se.rich.tidtagninig.system.entity;

import org.junit.Assert;
import org.junit.Test;
import se.rich.grtiming.system.entity.Address;
import se.rich.tidtagninig.support.AssertAnnotations;
import se.rich.tidtagninig.support.ReflectTool;

import javax.persistence.*;

public class AddressTest implements TestEntityInterface {

    @Override
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(Address.class, NamedQueries.class, Entity.class, Table.class);
    }

    @Override
    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Address.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Override
    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Address.class, Table.class);
        Assert.assertEquals("address", t.name());
    }

    @Override
    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Address.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Address.class, "email", Column.class);
        AssertAnnotations.assertField(Address.class, "phone", Column.class);
    }

    @Override
    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Address.class, "getId");
        AssertAnnotations.assertMethod(Address.class, "getEmail");
        AssertAnnotations.assertMethod(Address.class, "getPhone");
    }
}