package com.analitics.managerialstaff.ui.view.navigations.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = CertificationsView.NAME)
public class CertificationsViewImpl extends VerticalLayout implements CertificationsView {

    @Autowired
    private EventBus.UIEventBus eventBus;

    private ComboBox certificationNameSelect;
    private ComboBox gradeSelect;
    private MHorizontalLayout controlButtonsLayout;
    private Button addCertificationButton;
    private Button deleteCertificationButton;
    private Button editCertificationButton;
    private Grid certificationsGrid;
    private BeanItemContainer<Certification> certificationContainer;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        initListeners();
        constructLayout();
    }

    private void initComponents() {
        initSelectComponents();
        initButtons();
        initGrid();
    }

    private void initSelectComponents() {
        certificationNameSelect = new ComboBox("Название аттестации");
        certificationNameSelect.setNullSelectionAllowed(false);
        certificationNameSelect.setWidth(250, Unit.PIXELS);
        certificationNameSelect.addItems(
                "2013/1",
                "2013/2",
                "2013/3",
                "2013/4",
                "2014/1",
                "2014/2",
                "2014/3",
                "2014/4",
                "2015/1",
                "2015/2",
                "2015/3",
                "2015/4"
        );
        certificationNameSelect.setValue("2013/1");

        gradeSelect = new ComboBox("Грейд");
        gradeSelect.setNullSelectionAllowed(false);
        gradeSelect.setWidth(250, Unit.PIXELS);
        gradeSelect.addItems(
                Grade.SPECIALIST,
                Grade.MANAGER
        );
        gradeSelect.setValue(Grade.SPECIALIST);
    }

    private void initButtons() {
        addCertificationButton = new MButton("Добавить").withIcon(FontAwesome.PLUS);
        editCertificationButton = new MButton("Изменить").withIcon(FontAwesome.PENCIL);
        deleteCertificationButton = new MButton("Удалить").withIcon(FontAwesome.TRASH_O);

        controlButtonsLayout = new MHorizontalLayout(
                addCertificationButton,
                editCertificationButton,
                deleteCertificationButton
        ).withMargin(false);
    }

    private void initGrid() {
        certificationContainer = new BeanItemContainer<>(Certification.class);
        certificationsGrid = new Grid("Список аттестации", certificationContainer);
        certificationsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        certificationsGrid.setImmediate(true);
        certificationsGrid.setWidth(99, Unit.PERCENTAGE);
        certificationsGrid.setColumnOrder(

        );
        removeUnusedColumns();
        setColumnsCaption();
    }

    private void removeUnusedColumns() {
        certificationsGrid.removeColumn("id");
        certificationsGrid.removeColumn(Certification.EMPLOYEE);
    }

    private void setColumnsCaption() {
        Grid.Column surnameColumn = certificationsGrid.getColumn(Certification.RESPONSIBILITY);
        surnameColumn.setHeaderCaption("Ответственность");
        Grid.Column forenameColumn = certificationsGrid.getColumn(Certification.COMPETENCE);
        forenameColumn.setHeaderCaption("Компетентность");
        Grid.Column genderColumn = certificationsGrid.getColumn(Certification.COMMUNICABILITY);
        genderColumn.setHeaderCaption("Коммуникабельность");
        Grid.Column ageColumn = certificationsGrid.getColumn(Certification.TEST_RESULT);
        ageColumn.setHeaderCaption("Результаты теста");
    }

    private void initListeners() {

    }

    private void constructLayout() {
        MVerticalLayout resultLayout = new MVerticalLayout(
                new MHorizontalLayout(
                        certificationNameSelect,
                        gradeSelect
                ).withMargin(false),
                controlButtonsLayout,
                certificationsGrid
        ).expand(certificationsGrid);
        addComponent(resultLayout);
    }

    @Override
    public void setCertifications(Iterable<Certification> certifications) {
        certificationContainer.removeAllItems();
        certificationContainer.addAll((Collection<? extends Certification>) certifications);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
