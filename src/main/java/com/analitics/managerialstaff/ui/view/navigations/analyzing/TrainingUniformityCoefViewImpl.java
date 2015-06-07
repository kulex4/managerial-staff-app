package com.analitics.managerialstaff.ui.view.navigations.analyzing;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.analitics.managerialstaff.ui.view.navigations.analyzing.dto.TrainingUniformityCoefRow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = TrainingUniformityCoefView.NAME)
public class TrainingUniformityCoefViewImpl extends VerticalLayout implements TrainingUniformityCoefView {

    private ComboBox yearSelect;
    private BeanItemContainer<TrainingUniformityCoefRow> beanItemContainer;
    private MTable<TrainingUniformityCoefRow> table;

    private Label coeficientUniformityOfTrainingLabel;
    private Label coeficientUniformityOfTrainingQuarter;
    private Label coeficientUniformityOfTrainingValue;
    private Label maxDensityLabel;
    private Label maxDensityQuarter;
    private Label maxDensityValue;
    private Label minDensityLabel;
    private Label minDensityQuarter;
    private Label minDensityValue;

    private static final int TOTAL_EMPLOYEES_CURRENT = 312;

    @PostConstruct
    private void init() {
        setSizeFull();
        initComponents();
        addGeneratedProperties();
        configureColumnsCaptions();
        constructLayout();
    }

    private void initComponents() {
        yearSelect = new ComboBox("Отчетный год");
        yearSelect.setWidth(300, Unit.PIXELS);
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.addItems("2015", "2014", "2013", "2012", "2011");
        yearSelect.select("2015");

        beanItemContainer = new BeanItemContainer<>(TrainingUniformityCoefRow.class);
        table = new MTable<>(TrainingUniformityCoefRow.class);
        table.setCaption("Анализ равномерности повышения квалификации специалистов");
        table.setSelectable(false);
        table.setWidth(100, Unit.PERCENTAGE);
        table.setHeight(300, Unit.PIXELS);
        table.setColumnReorderingAllowed(true);

        coeficientUniformityOfTrainingLabel = new Label("Коэффициент равномерности повышения квалификации:");
        coeficientUniformityOfTrainingQuarter = new Label("   ");
        coeficientUniformityOfTrainingValue = new Label("0,8814");
        coeficientUniformityOfTrainingValue.setStyleName(MyTheme.LABEL_COLORED);

        maxDensityLabel = new Label("Наибольший удельный вес приходится на:");
        maxDensityQuarter = new Label("2 квартал");
        maxDensityValue = new Label("36,23 %");
        maxDensityValue.setStyleName(MyTheme.LABEL_COLORED);

        minDensityLabel = new Label("Наименший удельный вес приходится на:");
        minDensityQuarter = new Label("1 квартал");
        minDensityValue = new Label("17,32 %");
        minDensityValue.setStyleName(MyTheme.LABEL_COLORED);
    }

    private void constructLayout() {
        MHorizontalLayout spacing = new MHorizontalLayout().withFullHeight().withFullWidth();
        MVerticalLayout rootLayout = new MVerticalLayout(
                yearSelect,
                table,
                new MHorizontalLayout().withMargin(false).withWidth("50px"),
                new MHorizontalLayout(
                        new MVerticalLayout(
                                coeficientUniformityOfTrainingLabel,
                                maxDensityLabel,
                                minDensityLabel
                        ).withMargin(false),
                        new MVerticalLayout().withMargin(false).withWidth("20px"),
                        new MVerticalLayout(
                                coeficientUniformityOfTrainingQuarter,
                                maxDensityQuarter,
                                minDensityQuarter
                        ).withMargin(false),
                        new MVerticalLayout().withMargin(false).withWidth("20px"),
                        new MVerticalLayout(
                                coeficientUniformityOfTrainingValue,
                                maxDensityValue,
                                minDensityValue
                        ).withMargin(false)
                ).withMargin(false),
                spacing
        ).withMargin(new MarginInfo(true, false, false, false)).withFullHeight().withFullWidth().expand(spacing);
        addComponent(rootLayout);
    }

    private void addGeneratedProperties() {
        GeneratedPropertyContainer generatedPropertyContainer = new GeneratedPropertyContainer(beanItemContainer);
        generatedPropertyContainer.addGeneratedProperty("goalEmployees", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                return (float) TOTAL_EMPLOYEES_CURRENT * 0.25F;
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("densityCurrent", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                float numberOfEmployees = (Integer) item.getItemProperty("numberOfEmployees").getValue();
                return numberOfEmployees * 100 / TOTAL_EMPLOYEES_CURRENT;
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("densityGoal", new PropertyValueGenerator<Integer>() {
            @Override
            public Integer getValue(Item item, Object o, Object o1) {
                return 25;
            }

            @Override
            public Class<Integer> getType() {
                return Integer.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("goalCoefficient", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                float numberOfEmployees = (Integer) item.getItemProperty("numberOfEmployees").getValue();
                return numberOfEmployees / (float) TOTAL_EMPLOYEES_CURRENT * 0.25F;
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("executionCondition", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                float goalEmployees = (float) TOTAL_EMPLOYEES_CURRENT * 0.25F;
                float numberOfEmployees = (Integer) item.getItemProperty("numberOfEmployees").getValue();
                return goalEmployees > numberOfEmployees ? numberOfEmployees : goalEmployees;
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });
        table.setContainerDataSource(generatedPropertyContainer);
    }

    private void configureColumnsCaptions() {
        table.setColumnHeader("name", "Квартал");
        table.setColumnHeader("numberOfEmployees", "Численность сотрудников, повысивших квалификацию фактическая");
        table.setColumnHeader("goalEmployees", "Численность сотрудников, повысивших квалификацию при условии равномерного погашения");
        table.setColumnHeader("densityCurrent", "Удельный вес, фактический (%)");
        table.setColumnHeader("densityGoal", "Удельный вес, при условии равномерного погашения (%)");
        table.setColumnHeader("goalCoefficient", "Выполнение условия равномерности, коэф.");
        table.setColumnHeader("executionCondition", "Численность сотрудников, повысивших квалификацию, за квартал, зачтенных в выполнение условий равномерности, человек");
    }

    @Override
    public void insertData(List<TrainingUniformityCoefRow> rows) {
        beanItemContainer.removeAllItems();
        beanItemContainer.addAll(rows);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
