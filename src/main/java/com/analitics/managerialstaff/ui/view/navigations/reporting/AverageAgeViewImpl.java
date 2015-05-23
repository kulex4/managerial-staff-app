package com.analitics.managerialstaff.ui.view.navigations.reporting;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = AverageAgeView.NAME)
public class AverageAgeViewImpl extends VerticalLayout implements AverageAgeView {

    private Chart stackedColumnChart;
    private Configuration stackedColumnChartConfiguration;

    @PostConstruct
    private void init() {
        setSizeFull();
    }

    @Override
    public void generateChart() {
        removeAllComponents();

        initPieChart();
        initConfiguration();
        setPlotAxis();
        setPlotLegend();
        setPlotOptions();
        setDataSeries();
        constructLayout();

        stackedColumnChart.drawChart(stackedColumnChartConfiguration);
    }

    private void initPieChart() {
        stackedColumnChart = new Chart(ChartType.BAR);
        stackedColumnChart.setWidth(900, Unit.PIXELS);
        stackedColumnChart.setHeight(600, Unit.PIXELS);
    }

    private void initConfiguration() {
        stackedColumnChartConfiguration = stackedColumnChart.getConfiguration();
        stackedColumnChartConfiguration.setTitle("Средний возраст сотрудников по отделам");
    }

    private void setPlotAxis() {
        XAxis xAxis = new XAxis();
        xAxis.setCategories(
                "Главный бухгалтер и его помощники",
                "Управление финансового учета и отчетности",
                "Управление производственного учета",
                "Отдел налоговой отчетности",
                "Управление консолидированной финансовой отчетности"
        );
        stackedColumnChartConfiguration.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new Title("Средний возраст, лет"));
        StackLabels sLabels = new StackLabels(true);
        yAxis.setStackLabels(sLabels);
        stackedColumnChartConfiguration.addyAxis(yAxis);
    }

    private void setPlotLegend() {
        Legend legend = new Legend();
        stackedColumnChartConfiguration.setLegend(legend);
    }

    private void setPlotOptions() {
        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        Labels labels = new Labels(true);
        labels.setColor(new SolidColor("white"));
        plotOptions.setDataLabels(labels);
        stackedColumnChartConfiguration.setPlotOptions(plotOptions);
    }

    private void setDataSeries() {
        stackedColumnChartConfiguration.addSeries(new ListSeries("До 30 лет", 0, 41, 12, 3, 22));
        stackedColumnChartConfiguration.addSeries(new ListSeries("От 30 до 40 лет", 4, 65, 20, 5, 10));
        stackedColumnChartConfiguration.addSeries(new ListSeries("От 40 до 50 лет", 2, 45, 86, 10, 5));
        stackedColumnChartConfiguration.addSeries(new ListSeries("От 50 до 60 лет", 0, 8, 30, 0, 0));
        stackedColumnChartConfiguration.addSeries(new ListSeries("Свыше 60 лет", 0, 2, 5, 0, 0));
    }

    private void constructLayout() {
        MVerticalLayout pieLayout = new MVerticalLayout(
                stackedColumnChart
        ).withMargin(true).withFullHeight().withFullWidth().alignAll(Alignment.TOP_CENTER);
        addComponent(pieLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
