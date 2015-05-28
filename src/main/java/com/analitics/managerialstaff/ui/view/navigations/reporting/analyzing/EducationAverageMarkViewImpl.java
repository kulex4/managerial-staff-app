package com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing.dto.EducationAverageMarkRow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = EducationAverageMarkView.NAME)
public class EducationAverageMarkViewImpl extends VerticalLayout implements EducationAverageMarkView {

    private ComboBox yearSelect;
    private BeanItemContainer<EducationAverageMarkRow> beanItemContainer;
    private Grid mainGrid;

    private static final int TOTAL_EMPLOYEES_CURRENT = 361;
    private static final int TOTAL_EMPLOYEES_PREVIOUS = 359;

    @PostConstruct
    private void init() {
        setSizeFull();
        initComponents();
        constructLayout();
    }

    private void initComponents() {
        yearSelect = new ComboBox("Отчетный год");
        yearSelect.setWidth(300, Unit.PIXELS);
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.addItems("2015", "2014", "2013", "2012", "2011");
        yearSelect.select("2015");

        beanItemContainer = new BeanItemContainer<>(EducationAverageMarkRow.class);
        mainGrid = new Grid("Образовательный уровень управленческого персонала");
        mainGrid.setSizeFull();
    }

    private void constructLayout() {
        MVerticalLayout rootLayout = new MVerticalLayout(
                yearSelect,
                mainGrid
        ).withMargin(false).withFullHeight().withFullWidth().expand(mainGrid);
        addComponent(rootLayout);
    }

    @Override
    public void insertData(List<EducationAverageMarkRow> averageMarkRows) {
        beanItemContainer.removeAllItems();
        beanItemContainer.addAll(averageMarkRows);

        GeneratedPropertyContainer generatedPropertyContainer = new GeneratedPropertyContainer(beanItemContainer);
        generatedPropertyContainer.addGeneratedProperty("percentPrevious", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                float numberOfEmployees = (Integer) item.getItemProperty("numberOfEmployeesPrevious").getValue();
                return (numberOfEmployees * 100 / TOTAL_EMPLOYEES_CURRENT);
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("percentCurrent", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                float numberOfEmployees = (Integer) item.getItemProperty("numberOfEmployeesCurrent").getValue();
                return (numberOfEmployees * 100 / TOTAL_EMPLOYEES_PREVIOUS);
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("numberOfDeviation", new PropertyValueGenerator<String>() {
            @Override
            public String getValue(Item item, Object o, Object o1) {
                int employeesPrevious = (Integer) item.getItemProperty("numberOfEmployeesPrevious").getValue();
                int employeesCurrent = (Integer) item.getItemProperty("numberOfEmployeesCurrent").getValue();
                int result = employeesCurrent - employeesPrevious;
                return result > 0 ? " + " + result : (result < 0 ? " - " + result : " - ");
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        });

        generatedPropertyContainer.addGeneratedProperty("growthRate", new PropertyValueGenerator<Float>() {
            @Override
            public Float getValue(Item item, Object o, Object o1) {
                float employeesPrevious = (Integer) item.getItemProperty("numberOfEmployeesPrevious").getValue();
                float employeesCurrent = (Integer) item.getItemProperty("numberOfEmployeesCurrent").getValue();
                return employeesCurrent * 100 / employeesPrevious;
            }

            @Override
            public Class<Float> getType() {
                return Float.class;
            }
        });
        mainGrid.setContainerDataSource(generatedPropertyContainer);
        configureColumnsCaptions();
        generateHeaderRow();
    }

    private void configureColumnsCaptions() {
        Grid.HeaderRow mainHeader = mainGrid.getDefaultHeaderRow();
        mainHeader.getCell("name").setText("Показатель");
        mainHeader.getCell("numberOfEmployeesPrevious").setText("человек");
        mainHeader.getCell("percentPrevious").setText("%");
        mainHeader.getCell("numberOfEmployeesCurrent").setText("человек");
        mainHeader.getCell("percentCurrent").setText("%");
        mainHeader.getCell("numberOfDeviation").setText("Абсолютное отклонение");
        mainHeader.getCell("growthRate").setText("Темп роста, %");
        mainHeader.setStyleName(MyTheme.GRID_BOLD_HEADER);

    }

    private void generateHeaderRow() {
        Grid.HeaderRow groupingHeader = mainGrid.prependHeaderRow();
        Grid.HeaderCell previousYearCells = groupingHeader.join(
                groupingHeader.getCell("numberOfEmployeesPrevious"),
                groupingHeader.getCell("percentPrevious"));
        previousYearCells.setHtml("Предыдущий год");
        Grid.HeaderCell currentYearCells = groupingHeader.join(
                groupingHeader.getCell("numberOfEmployeesCurrent"),
                groupingHeader.getCell("percentCurrent"));
        currentYearCells.setHtml("Отчетный год");
        mainGrid.setColumnOrder(
                "name",
                "numberOfEmployeesPrevious",
                "percentPrevious",
                "numberOfEmployeesCurrent",
                "percentCurrent",
                "numberOfDeviation",
                "growthRate"
        );
        groupingHeader.setStyleName(MyTheme.GRID_BOLD_HEADER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
