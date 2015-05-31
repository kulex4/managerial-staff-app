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
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
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
    private Grid mainGrid;

    private static final int TOTAL_EMPLOYEES_CURRENT = 312;

    @PostConstruct
    private void init() {
        setSizeFull();
        initComponents();
        addGeneratedProperties();
        configureColumnsCaptions();
        generateHeaderRow();
        constructLayout();
    }

    private void initComponents() {
        yearSelect = new ComboBox("Отчетный год");
        yearSelect.setWidth(300, Unit.PIXELS);
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.addItems("2015", "2014", "2013", "2012", "2011");
        yearSelect.select("2015");

        beanItemContainer = new BeanItemContainer<>(TrainingUniformityCoefRow.class);
        mainGrid = new Grid("Анализ равномерности повышения квалификации специалистов");
        mainGrid.setSelectionMode(Grid.SelectionMode.NONE);
        mainGrid.setStyleName(MyTheme.TURN_OFF_SCROLLBAR);
        mainGrid.setWidth(100, Unit.PERCENTAGE);
        mainGrid.setHeight(250, Unit.PIXELS);
    }

    private void constructLayout() {
        MHorizontalLayout spacing = new MHorizontalLayout().withFullHeight().withFullWidth();
        MVerticalLayout rootLayout = new MVerticalLayout(
                yearSelect,
                mainGrid,
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
        mainGrid.setContainerDataSource(generatedPropertyContainer);
    }

    private void configureColumnsCaptions() {
        Grid.HeaderRow mainHeader = mainGrid.getDefaultHeaderRow();
        mainHeader.getCell("name").setText("Квартал");
        mainHeader.getCell("numberOfEmployees").setText("фактическая");
        mainHeader.getCell("goalEmployees").setText("при условии\n равномерного погашения");
        mainHeader.getCell("densityCurrent").setText("фактическая");
        mainHeader.getCell("densityGoal").setText("при условии равномерного погашения");
        mainHeader.getCell("goalCoefficient").setText("Выполнение условия равномерности, коэф.");
        mainHeader.getCell("executionCondition").setText("Численность сотрудников повысивших квалификацию");
        mainHeader.setStyleName(MyTheme.GRID_BOLD_HEADER);

        Grid.Column mainColumn = mainGrid.getColumn("goalEmployees");
        mainColumn.setMaximumWidth(10);
    }

    private void generateHeaderRow() {
        Grid.HeaderRow groupingHeader = mainGrid.prependHeaderRow();
        Grid.HeaderCell numberOfEmployeesCells = groupingHeader.join(
                groupingHeader.getCell("numberOfEmployees"),
                groupingHeader.getCell("goalEmployees"));
        numberOfEmployeesCells.setHtml("Численность сотрудников, повысивших квалификацию");
        Grid.HeaderCell densityCells = groupingHeader.join(
                groupingHeader.getCell("densityCurrent"),
                groupingHeader.getCell("densityGoal"));
        densityCells.setHtml("Удельный вес, %");
        mainGrid.setColumnOrder(
                "name",
                "numberOfEmployees",
                "goalEmployees",
                "densityCurrent",
                "densityGoal",
                "goalCoefficient",
                "executionCondition"
        );
    }

    @Override
    public void insertData(List<TrainingUniformityCoefRow> rows) {
        beanItemContainer.removeAllItems();
        beanItemContainer.addAll(rows);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
