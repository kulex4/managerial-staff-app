package com.analitics.managerialstaff.ui.view.navigations.reporting;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
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
@VaadinView(name = GenderView.NAME)
public class GenderViewImpl extends VerticalLayout implements GenderView {

    private Chart pieChart;
    private Configuration pieChartConfiguration;

    @PostConstruct
    private void init() {
        setSizeFull();
        initPieChart();
    }

    @Override
    public void generateChart() {
        removeAllComponents();
        initConfiguration();
        setPlotOptions();
        setDataSeries();
        constructLayout();

        pieChart.drawChart(pieChartConfiguration);
    }

    private void initPieChart() {
        pieChart = new Chart(ChartType.PIE);
        pieChart.setWidth(600, Unit.PIXELS);
        pieChart.setHeight(600, Unit.PIXELS);
    }

    private void initConfiguration() {
        pieChartConfiguration = pieChart.getConfiguration();
        pieChartConfiguration.setTitle("Образование наших сотрудников");
    }

    private void setPlotOptions() {
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setShowInLegend(true);
        pieChartConfiguration.setPlotOptions(plotOptions);
    }

    private void setDataSeries() {
        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Высшее", 85.1));
        series.add(new DataSeriesItem("Средне-специальное", 7.6));
        DataSeriesItem slicedItem = new DataSeriesItem("Среднее", 7.3);
        slicedItem.setSliced(true);
        slicedItem.setSelected(true);
        series.add(slicedItem);
        pieChartConfiguration.setSeries(series);
    }

    private void constructLayout() {
        MVerticalLayout pieLayout = new MVerticalLayout(
                pieChart
        ).withMargin(true).withFullHeight().withFullWidth().alignAll(Alignment.TOP_CENTER);
        addComponent(pieLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
