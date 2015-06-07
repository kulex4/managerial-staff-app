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
@VaadinView(name = ExperienceView.NAME)
public class ExperienceViewImpl extends VerticalLayout implements ExperienceView {

    private Chart columnChart;
    private Configuration columnChartConfiguration;

    @PostConstruct
    private void init() {
        setSizeFull();
    }

    @Override
    public void generateChart() {
        removeAllComponents();

        initColumnChart();
        initConfiguration();
        setPlotAxis();
        setPlotLegend();
        setDataSeries();
        constructLayout();

        columnChart.drawChart(columnChartConfiguration);
    }

    private void initColumnChart() {
        columnChart = new Chart(ChartType.COLUMN);
        columnChart.setWidth(900, Unit.PIXELS);
        columnChart.setHeight(600, Unit.PIXELS);
    }

    private void initConfiguration() {
        columnChartConfiguration = columnChart.getConfiguration();
        columnChartConfiguration.setTitle("Средний стаж сотрудников по отделам");
    }

    private void setPlotAxis() {
        XAxis xAxis = new XAxis();
        xAxis.setCategories(
                "Главный бухгалтер и его помощники",
                "Управление финансового учета и отчетности",
                "Управление производственного учета",
                "Отдел налоговой отчетности",
                "Управление консолидированной финансовой отчетности",
                "Итого"
        );
        columnChartConfiguration.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new Title("Средний стаж, лет"));
        columnChartConfiguration.addyAxis(yAxis);
    }

    private void setPlotLegend() {
        Legend legend = new Legend();
        legend.setEnabled(false);
        columnChartConfiguration.setLegend(legend);
    }

    private void setDataSeries() {
        ListSeries serie = new ListSeries("Средний стаж", 10.8, 11.3, 13.9, 9.17, 5.6, 11.7);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setRotation(-90);
        dataLabels.setColor(new SolidColor(255, 255, 255));
        dataLabels.setAlign(HorizontalAlign.RIGHT);
        dataLabels.setX(4);
        dataLabels.setY(10);
        dataLabels.setFormatter("this.y");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setDataLabels(dataLabels);
        serie.setPlotOptions(plotOptionsColumn);
        columnChartConfiguration.addSeries(serie);
    }

    private void constructLayout() {
        MVerticalLayout pieLayout = new MVerticalLayout(
                columnChart
        ).withMargin(true).withFullHeight().withFullWidth().alignAll(Alignment.TOP_CENTER);
        addComponent(pieLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
