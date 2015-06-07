package com.analitics.managerialstaff.ui.view.navigations.analyzing;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.analitics.managerialstaff.ui.view.navigations.analyzing.dto.EducationAverageMarkRow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.layouts.MHorizontalLayout;
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

    private TextArea specificationTextArea;
    private Label factorGpaPreviousYear;
    private Label factorGpaPreviousYearNumber;
    private Label factorGpaCurrentYear;
    private Label factorGpaCurrentYearNumber;
    private Label growthRateOfEducationLevel;
    private Label growthRateOfEducationLevelNumber;
    private Label absoluteGrowthRateReserve;
    private Label absoluteGrowthRateReserveNumber;
    private Label relativeGrowthRateReserve;
    private Label relativeGrowthRateReserveNumber;

    private static final int TOTAL_EMPLOYEES_CURRENT = 361;
    private static final int TOTAL_EMPLOYEES_PREVIOUS = 359;

    @PostConstruct
    private void init() {
        setSizeFull();
        initComponents();
        addGeneratedProperties();
        configureColumnsCaptions();
        generateHeaderRow();
        constructLayout();
        settingValueOfSpecificationTextArea();

        generateFactorGpaPreviousYearLabel();
        generateFactorGpaCurrentYearLabel();
        generateGrowthRateOfEducationLevelLabel();
        generateAbsoluteGrowthRateReserveLabel();
        generateRelativeGrowthRateReserveLabel();
    }

    private void initComponents() {
        yearSelect = new ComboBox("Отчетный год");
        yearSelect.setWidth(300, Unit.PIXELS);
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.addItems("2015", "2014", "2013", "2012", "2011");
        yearSelect.select("2015");

        beanItemContainer = new BeanItemContainer<>(EducationAverageMarkRow.class);
        mainGrid = new Grid("Образовательный уровень управленческого персонала");
        mainGrid.setSelectionMode(Grid.SelectionMode.NONE);
        mainGrid.setWidth(100, Unit.PERCENTAGE);
        mainGrid.setHeight(250, Unit.PIXELS);

        specificationTextArea = new MTextArea().withFullWidth().withRows(4);
        specificationTextArea.setStyleName(MyTheme.TEXTAREA_BORDERLESS);

        factorGpaPreviousYear = new Label();
        factorGpaPreviousYearNumber = new Label();
        factorGpaPreviousYearNumber.setStyleName(MyTheme.LABEL_COLORED);

        factorGpaCurrentYear = new Label();
        factorGpaCurrentYearNumber = new Label();
        factorGpaCurrentYearNumber.setStyleName(MyTheme.LABEL_COLORED);

        growthRateOfEducationLevel = new Label();
        growthRateOfEducationLevelNumber = new Label();
        growthRateOfEducationLevelNumber.setStyleName(MyTheme.LABEL_COLORED);

        absoluteGrowthRateReserve = new Label();
        absoluteGrowthRateReserveNumber = new Label();
        absoluteGrowthRateReserveNumber.setStyleName(MyTheme.LABEL_COLORED);

        relativeGrowthRateReserve = new Label();
        relativeGrowthRateReserveNumber = new Label();
        relativeGrowthRateReserveNumber.setStyleName(MyTheme.LABEL_COLORED);
    }

    private void constructLayout() {
        MHorizontalLayout spacing = new MHorizontalLayout().withFullHeight().withFullWidth();
        MVerticalLayout rootLayout = new MVerticalLayout(
                yearSelect,
                mainGrid,
                specificationTextArea,
                new MHorizontalLayout(
                        new MVerticalLayout(
                                factorGpaPreviousYear,
                                factorGpaCurrentYear,
                                growthRateOfEducationLevel,
                                absoluteGrowthRateReserve,
                                relativeGrowthRateReserve
                        ).withMargin(false),
                        new MVerticalLayout(
                                factorGpaPreviousYearNumber,
                                factorGpaCurrentYearNumber,
                                growthRateOfEducationLevelNumber,
                                absoluteGrowthRateReserveNumber,
                                relativeGrowthRateReserveNumber
                        ).withMargin(false)
                ).withMargin(false),
                spacing
        ).withMargin(new MarginInfo(true, false, false, false)).withFullHeight().withFullWidth().expand(spacing);
        addComponent(rootLayout);
    }

    private void addGeneratedProperties() {
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
    }

    @Override
    public void insertData(List<EducationAverageMarkRow> averageMarkRows) {
        beanItemContainer.removeAllItems();
        beanItemContainer.addAll(averageMarkRows);
    }

    private void settingValueOfSpecificationTextArea() {
        String text = "Отмечая приорететность высшего экономического образования, его ранжирование по методу среднего бала следующее: \n"
                + "          5 баллов - экономическое образование;\n"
                + "          4 балла - техническое образование;\n"
                + "          3 балла - гуманитарное образование;";
        specificationTextArea.setValue(text);
        specificationTextArea.setReadOnly(true);
    }

    private void generateFactorGpaPreviousYearLabel() {
        factorGpaPreviousYear.setValue("Коэффициент среднего балла для 2012 года:");
        factorGpaPreviousYearNumber.setValue("4,69");
    }

    private void generateFactorGpaCurrentYearLabel() {
        factorGpaCurrentYear.setValue("Коэффициент среднего балла для 2013 года: ");
        factorGpaCurrentYearNumber.setValue("4,71");
    }

    private void generateGrowthRateOfEducationLevelLabel() {
        growthRateOfEducationLevel.setValue("Коэффициент роста образовательного уровня: ");
        growthRateOfEducationLevelNumber.setValue("0,43%");
    }

    private void generateAbsoluteGrowthRateReserveLabel() {
        absoluteGrowthRateReserve.setValue("Абсолютный резерв роста образовательного уровня, коэф.: ");
        absoluteGrowthRateReserveNumber.setValue("0,29");
    }

    private void generateRelativeGrowthRateReserveLabel() {
        relativeGrowthRateReserve.setValue("Относительный  резерв роста образовательного уровня: ");
        relativeGrowthRateReserveNumber.setValue("6,16%");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
