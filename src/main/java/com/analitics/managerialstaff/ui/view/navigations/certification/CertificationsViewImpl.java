package com.analitics.managerialstaff.ui.view.navigations.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
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

    private ComboBox yearSelect;
    private ComboBox quarterSelect;
    private ComboBox gradeSelect;
    private MHorizontalLayout controlButtonsLayout;
    private Button addCertificationButton;
    private Button deleteCertificationButton;
    private Button editCertificationButton;
    private Table certificationTable;
    private BeanItemContainer<Certification> certificationContainer;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        constructLayout();
    }

    private void initComponents() {
        initSelectComponents();
        initButtons();
        initTable();
    }

    private void initSelectComponents() {
        yearSelect = new ComboBox("Год");
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.setWidth(100, Unit.PIXELS);
        yearSelect.addItems(
                "2015",
                "2014",
                "2013",
                "2012"
        );
        yearSelect.select("2015");

        quarterSelect = new ComboBox("Квартал");
        quarterSelect.setNullSelectionAllowed(false);
        quarterSelect.setWidth(80, Unit.PIXELS);
        quarterSelect.addItems(
                Quarter.FIRST,
                Quarter.SECOND,
                Quarter.THIRD,
                Quarter.FOURTH
        );
        quarterSelect.select(Quarter.FIRST);

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

    private void initTable() {
        certificationContainer = new BeanItemContainer<>(Certification.class);
        certificationTable = new Table("Аттестация сотрудников", certificationContainer);
        certificationTable.setImmediate(true);
        certificationTable.setWidth(100, Unit.PERCENTAGE);
        setupTableColumns();
    }

    private void setupTableColumns() {
        certificationContainer.addNestedContainerBean(Certification.EMPLOYEE);

        certificationTable.setColumnHeader(Certification.EMPLOYEE_SURNAME, "Фамилия");
        certificationTable.setColumnHeader(Certification.EMPLOYEE_FORENAME, "Имя");
        certificationTable.setColumnHeader(Certification.EMPLOYEE_POSITION, "Должность");
        certificationTable.setColumnHeader(Certification.RESPONSIBILITY, "Ответственность");
        certificationTable.setColumnHeader(Certification.COMPETENCE, "Компетентность");
        certificationTable.setColumnHeader(Certification.COMMUNICABILITY, "Коммуникабельность");
        certificationTable.setColumnHeader(Certification.TEST_RESULT, "Результаты теста");
    }

    private void constructLayout() {
        MVerticalLayout resultLayout = new MVerticalLayout(
                new MHorizontalLayout(
                        yearSelect,
                        quarterSelect,
                        gradeSelect
                ).withMargin(false),
                controlButtonsLayout,
                certificationTable
        ).expand(certificationTable);
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
