package ru.job4j.lsp;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

class WarehouseStrategyTest {
    @Test
    void whenShelfLifeIsLessThan25PercentThenDistributeToWarehouse() {
        var warehouse = new WarehouseStrategy();
        var control = new QualityControlContext(warehouse);

        CurrencyUnit usd = Monetary.getCurrency("USD");
        List<Product> products = new ArrayList<>(
                List.of(
                        new Bread(
                                "some bread",
                                LocalDate.now().minusDays(1),
                                LocalDate.now().plusDays(5),
                                Money.of(5, usd),
                                0D
                        ),
                        new Water(
                                "some water",
                                LocalDate.now().minusDays(20),
                                LocalDate.now().plusDays(20),
                                Money.of(2, usd),
                                0D
                        ),
                        new Meat(
                                "some meat",
                                LocalDate.now().minusDays(4),
                                LocalDate.now().plusDays(1),
                                Money.of(20, usd),
                                0D
                        ),
                        new Fruit(
                                "some fruit",
                                LocalDate.now().minusDays(11),
                                LocalDate.now().minusDays(1),
                                Money.of(10, usd),
                                0D
                        )
                )
        );

        control.executeStrategy(products);

        assertThat(warehouse.getProducts().size(), is(1));
        assertThat(warehouse.getProducts().get(0).getName(), is("some bread"));
        assertThat(warehouse.getProducts().get(0).getDiscount(), is(0D));
        assertThat(products.size(), is(3));
    }
}